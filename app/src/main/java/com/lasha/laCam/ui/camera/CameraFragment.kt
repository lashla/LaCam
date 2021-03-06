package com.lasha.laCam.ui.camera

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.lasha.laCam.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.camera_fragment.*
import java.text.SimpleDateFormat
import java.util.*


@AndroidEntryPoint
class CameraFragment  : Fragment(R.layout.camera_fragment) {

    private lateinit var viewModel: CameraViewModel
    private var imageCapture: ImageCapture? = null
    private lateinit var fileName: String
    private lateinit var filePath: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CameraViewModel::class.java]
        checkPermissions()
        setupCaptureImageButton()
        setupGalleryBtn()
    }




    private fun setupCaptureImageButton(){
        captureImage.setOnClickListener {
            takePhoto()
        }
    }



    private fun startCamera(){
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val preview = Preview.Builder()
                .build()
                .also {
                    it.setSurfaceProvider(previewView.surfaceProvider)
                }

            imageCapture = ImageCapture.Builder().build()

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCapture)
            } catch (e: Exception) {
                Log.e(TAG, "Use case bind failed", e)
            }
        }, ContextCompat.getMainExecutor(requireContext()))

    }

    private fun takePhoto() {
        fileName = SimpleDateFormat(FILENAME_FORMAT, Locale.US)
            .format(System.currentTimeMillis())
        val path = "Pictures/LasCam-Images"
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, fileName)
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.MediaColumns.RELATIVE_PATH, path)
        }
        filePath = MediaStore.MediaColumns.RELATIVE_PATH + "/Pictures/LasCam-Images"
        val outputOptions = ImageCapture.OutputFileOptions
            .Builder(requireContext().contentResolver,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                contentValues)
            .build()

        imageCapture?.takePicture(
            outputOptions,
            ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {
                override fun onError(exception: ImageCaptureException) {
                    Log.e(TAG, "Photo capture failed: ${exception.message}", exception)
                }

                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val msg = "Photo capture succeeded: ${outputFileResults.savedUri}"
                    viewModel.insertHandler(filePath,fileName, outputFileResults.savedUri.toString())
                    Log.d(TAG, msg)
                }
            }
        )
    }

    private fun checkPermissions(){
        if (!allPermissionsGranted()){
            ActivityCompat.requestPermissions(requireActivity(),
                arrayOf(Manifest.permission.CAMERA), REQUEST_CODE_PERMISSIONS
            );
        } else {
            startCamera()
        }
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }
    companion object {
        private const val TAG = "CameraX"
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS =
            mutableListOf (
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ).apply {
            }.toTypedArray()
    }
    private fun setupGalleryBtn(){
        galleryView.setOnClickListener {
            Navigation.findNavController(requireView()).navigate(R.id.action_cameraFragment_to_galleryFragment)
        }
    }
}