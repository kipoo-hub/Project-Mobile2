package com.example.project_mobile.Home.pertemuan_13

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.project_mobile.R
import com.example.project_mobile.databinding.FragmentTabScanBinding
import com.example.project_mobile.utils.PermissionHelper
import com.google.mlkit.vision.barcode.BarcodeScanner
import com.google.mlkit.vision.barcode.BarcodeScanning
import com.google.mlkit.vision.common.InputImage
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class TabScanFragment : Fragment() {
    private var _binding: FragmentTabScanBinding? = null
    private val binding get() = _binding!!

    private lateinit var cameraExecutor: ExecutorService
    private var scanner: BarcodeScanner? = null

    private val permissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
        if (isGranted) {
            startCamera()
        } else {
            Toast.makeText(context, "Izin kamera diperlukan", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTabScanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        cameraExecutor = Executors.newSingleThreadExecutor()
        scanner = BarcodeScanning.getClient()

        if (!PermissionHelper.hasPermission(requireContext(), Manifest.permission.CAMERA)) {
            PermissionHelper.requestPermission(
                permissionLauncher,
                Manifest.permission.CAMERA
            )
        } else {
            startCamera()
        }
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder().build().apply {
                setSurfaceProvider(binding.previewView.surfaceProvider)
            }

            val imageAnalyzer = ImageAnalysis.Builder()
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .apply {
                    setAnalyzer(cameraExecutor) { imageProxy ->
                        val mediaImage = imageProxy.image ?: return@setAnalyzer
                        val image = InputImage.fromMediaImage(mediaImage, imageProxy.imageInfo.rotationDegrees)

                        scanner?.process(image)
                            ?.addOnSuccessListener { barcodes ->
                                if (barcodes.isNotEmpty()) {
                                    val rawValue = barcodes[0].rawValue
                                    activity?.runOnUiThread {
                                        // MEMPERBAIKI WARNING BARIS 89: Menggunakan String Resource
                                        binding.tvScanResult.text = getString(R.string.hasil_scan, rawValue)

                                        // *Catatan: Jika Anda tidak ingin menggunakan strings.xml sekarang
                                        // dan hanya ingin menghilangkan garis kuning sementara,
                                        // hapus baris di atas dan gunakan baris di bawah ini:
                                        // binding.tvScanResult.text = buildString { append("Hasil: ").append(rawValue) }
                                    }
                                }
                            }
                            ?.addOnCompleteListener { imageProxy.close() }
                    }
                }

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, CameraSelector.DEFAULT_BACK_CAMERA, preview, imageAnalyzer)
            } catch (e: Exception) {
                Log.e("TabScan", "Gagal mulai kamera", e)
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        scanner?.close()
        cameraExecutor.shutdown()
    }
}