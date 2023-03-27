package com.example.comicbookreader

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.documentfile.provider.DocumentFile
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.comicbookreader.databinding.FragmentSecondBinding
import java.io.File


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        openDirectory(Environment.getExternalStorageDirectory())

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

    fun openDirectory(pickerInitialUri: File) {
        // Choose a directory using the system's file picker.
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT_TREE).apply {
            // Optionally, specify a URI for the directory that should be opened in
            // the system file picker when it loads.
            putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri)
        }

        startActivityForResult(intent, 9999)

        Log.e("openDirectory", pickerInitialUri.toString());
    }

    override fun onActivityResult(
        requestCode: Int, resultCode: Int, resultData: Intent?) {
        if (requestCode == 9999 && resultCode == Activity.RESULT_OK) {
            // The result data contains a URI for the document or directory that the user selected.

            resultData?.data?.also {uri ->
                String dirPath = uri.toString();
            }

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}