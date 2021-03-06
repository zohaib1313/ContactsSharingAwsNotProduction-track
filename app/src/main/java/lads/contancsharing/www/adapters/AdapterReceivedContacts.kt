package lads.contancsharing.www.adapters


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amplifyframework.datastore.generated.model.UserContactSharing
import com.bumptech.glide.Glide
import lads.contancsharing.www.R
import lads.contancsharing.www.callBacks.OnItemClickListener
import lads.contancsharing.www.databinding.RowReceiveContactsSaveBinding
import lads.contancsharing.www.models.ModelReceivedContacts
import lads.contancsharing.www.models.ModelSharingContactWith
import lads.contancsharing.www.utils.Helper
import java.util.*


class AdapterReceivedContacts(
    var mContext: Context,
    var dataList: List<ModelReceivedContacts>
) :
    RecyclerView.Adapter<AdapterReceivedContacts.MyViewHolder>() {

    internal var mOnItemClickListener: OnItemClickListener? = null

    inner class MyViewHolder(val binding: RowReceiveContactsSaveBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
            binding.btnViewDownload.setOnClickListener(this)
        }

        override fun onClick(view: View) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener?.onItemClick(
                    view,
                    adapterPosition,
                    dataList[adapterPosition].toString()
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            RowReceiveContactsSaveBinding.inflate(LayoutInflater.from(mContext), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        with(holder) {
            with(dataList[position]) {
                binding.tvName.text = user.name
                Log.d("Taaag", dateTime)
                binding.tvDateTime.text = Helper.getAwsDate(contactSharingWith.fileTime.toLong())
                binding.tvSize.text = "${size.toString()} B"
                user.image?.let {
                    Glide.with(mContext).load(Helper.getImageUrl(it.toString()))
                        .placeholder(R.drawable.eclipse)
//                    .skipMemoryCache(true)
//                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .into(binding.ivContact)
                }


            }
        }
    }

    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = onItemClickListener
    }

}