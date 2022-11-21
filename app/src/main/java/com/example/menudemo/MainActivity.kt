package com.example.menudemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import com.example.menudemo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        popupMenu()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.options_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.print -> {
                makeToast("Printing...")
                true
            }
            R.id.share -> {
                makeToast("Sharing...")
                true
            }
            R.id.save -> {
                makeToast("Saved")
                true
            }
            R.id.delete -> {
                makeToast("Deleted")
                true
            }
            R.id.cancel -> {
                makeToast("Cancelled")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun popupMenu() {
        val popup = PopupMenu(this, binding.button2)
        popup.menuInflater.inflate(R.menu.popup_menu, popup.menu)
        popup.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.create -> makeToast(getString(R.string.create))
                R.id.destroy -> makeToast(getString(R.string.destroy))
                R.id.cancel -> makeToast(getString(R.string.cancel))
            }
            true
        }
        binding.button2.setOnClickListener { popup.show() }
    }

    private fun initView() {
        registerForContextMenu(binding.button1)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        val inflater = menuInflater
        inflater.inflate(R.menu.context_menu_demo, menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save -> {
                makeToast("Saved!")
                true
            }
            R.id.delete -> {
                makeToast("Deleted")
                true
            }
            R.id.cancel -> {
                makeToast("Cancelled")
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    private fun makeToast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }
}