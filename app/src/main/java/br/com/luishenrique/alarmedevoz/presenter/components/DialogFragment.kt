package br.com.luishenrique.alarmedevoz.presenter.components

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import br.com.luishenrique.alarmedevoz.R
import kotlinx.android.synthetic.main.fragment_dialog.view.*

class DialogFragment(private val onPositiveButtonClick: () -> Unit) : DialogFragment() {

    private val title: String by lazy {
        arguments?.getString(KEY_TITLE) as String
    }
    private val message: String by lazy {
        arguments?.getString(KEY_MESSAGE) as String
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val alertDialog = AlertDialog.Builder(requireContext())
        val view: View = requireActivity().layoutInflater
            .inflate(
                R.layout.fragment_dialog,
                null
            )

        alertDialog.setView(view)
        view.dialogTitle.text = title
        view.dialogMessage.text = message
        view.dialogNegativeButton.setOnClickListener {  }
        view.dialogPositiveButton.setOnClickListener {
            onPositiveButtonClick.invoke()
            dismiss()
        }

        return alertDialog.create()
    }

    companion object {
        const val KEY_MESSAGE = "KEY_MESSAGE"
        const val KEY_TITLE = "KEY_TITLE"
        const val TAG = "TAG_DIALOG_FRAGMENT"

        fun instance(
            message: String,
            title: String,
            onPositiveButtonClick: () -> Unit
        ): DialogFragment {

            val bundle = Bundle().apply {
                putString(KEY_MESSAGE, message)
                putString(KEY_TITLE, title)
            }

            return DialogFragment(onPositiveButtonClick).apply {
                arguments = bundle
            }
        }
    }
}