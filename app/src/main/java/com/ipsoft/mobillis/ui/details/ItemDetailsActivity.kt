package com.ipsoft.mobillis.ui.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ipsoft.mobillis.R
import com.ipsoft.mobillis.databinding.ActivityItemDetailsBinding

class ItemDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityItemDetailsBinding
    private val itemId: Long by lazy { intent.getLongExtra(EXTRA_ITEM_ID, -1) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityItemDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (savedInstanceState == null) {
            showItemDetailsFragment()
        }
    }

    private fun showItemDetailsFragment() {
        val fragment = ItemDetailsFragment.newInstance(itemId)
        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.details, fragment,
                ItemDetailsFragment.TAG_DETAILS
            )
            .commit()
    }

    companion object {
        private const val EXTRA_ITEM_ID = "item_id"
        fun open(activity: Activity, hotelId: Long) {
            activity.startActivityForResult(
                Intent(activity, ItemDetailsActivity::class.java).apply {
                    putExtra(EXTRA_ITEM_ID, hotelId)
                }, 0
            )
        }
    }
}