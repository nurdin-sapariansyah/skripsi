package com.masden.skripsi.view.profile

import android.app.ProgressDialog
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.masden.skripsi.R
import com.masden.skripsi.view.jadwal.ClientAsyncTask
import com.masden.skripsi.view.jadwal.DaftarKota
import kotlinx.android.synthetic.main.fragment_jadwal.*
import org.json.JSONException
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [NewsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class JadwalFragment : BottomSheetDialogFragment() {

    lateinit var strArg: String
    lateinit var listDaftarKota: MutableList<DaftarKota>
    lateinit var daftarKotaAdapter: ArrayAdapter<DaftarKota>
    lateinit var progressDialog: ProgressDialog

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (requireView().parent as View).setBackgroundColor(Color.TRANSPARENT)
    }

    companion object {
        @JvmStatic
        fun newInstance(string: String?): JadwalFragment {
            val fragment = JadwalFragment()
            val args = Bundle()
            args.putString("Jadwal Sholat", string)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        strArg = requireArguments().getString("Jadwal Sholat").toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val rootView: View = inflater.inflate(R.layout.fragment_jadwal, container, false)
        progressDialog = ProgressDialog(activity)
        progressDialog.setTitle("Mohon Tunggu")
        progressDialog.setCancelable(false)
        progressDialog.setMessage("Sedang menampilkan jadwal...")

        //show data spinner
        val spKota: Spinner = rootView.findViewById(R.id.spinKota)
        listDaftarKota = ArrayList()
        daftarKotaAdapter = ArrayAdapter(
            requireActivity().getApplicationContext(),
            android.R.layout.simple_spinner_item,
            listDaftarKota as ArrayList<DaftarKota>
        )
        daftarKotaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spKota.adapter = daftarKotaAdapter
        spKota.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>) {}
            override fun onItemSelected(p0: AdapterView<*>, view: View?, position: Int, id: Long) {
                val spinKota = daftarKotaAdapter.getItem(position)
                getDataJadwal(spinKota?.id)
            }
        }

        //show date time
//        val datePickerTimeline: DatePickerTimeline = rootView.findViewById(R.id.dateTimeline)
//        val date = Calendar.getInstance()
//        val mYear: Int = date.get(Calendar.YEAR)
//        val mMonth: Int = date.get(Calendar.MONTH)
//        val mDay: Int = date.get(Calendar.DAY_OF_MONTH)
//
//        datePickerTimeline.setInitialDate(mYear, mMonth, mDay)
//        datePickerTimeline.setDisabledDateColor(
//            ContextCompat.getColor(
//                requireActivity(),
//                R.color.teal_700
//            )
//        )
//        datePickerTimeline.setActiveDate(date)
//
//        val dates = arrayOf(Calendar.getInstance().time)
//        datePickerTimeline.deactivateDates(dates)

        //get data kota
        getDataKota()

        return rootView
    }

    private fun getDataJadwal(id: Int?) {
        try {
            progressDialog.show()
            val idKota = id.toString()
            val current = SimpleDateFormat("yyyy-MM-dd")
            val tanggal = current.format(Date())
            val url = "https://api.banghasan.com/sholat/format/json/jadwal/kota/$idKota/tanggal/$tanggal"
            val task = ClientAsyncTask(this, object : ClientAsyncTask.OnPostExecuteListener {
                override fun onPostExecute(result: String) {
                    try {
                        progressDialog.dismiss()
                        val jsonObject = JSONObject(result)
                        val strJadwal = jsonObject.getJSONObject("jadwal")
                        val strData = strJadwal.getJSONObject("data")

                        tvSubuh.text = strData.getString("subuh")
                        tvDzuhur.text = strData.getString("dzuhur")
                        tvAshar.text = strData.getString("ashar")
                        tvMaghrib.text = strData.getString("maghrib")
                        tvIsya.text = strData.getString("isya")
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            })
            task.execute(url)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getDataKota() {
        try {
            progressDialog.show()
            val url = "https://api.banghasan.com/sholat/format/json/kota"
            val task = ClientAsyncTask(this, object : ClientAsyncTask.OnPostExecuteListener {
                override fun onPostExecute(result: String) {
                    try {
                        progressDialog.dismiss()
                        val jsonObject = JSONObject(result)
                        val jsonArray = jsonObject.getJSONArray("kota")
                        var daftarKota: DaftarKota?
                        for (i in 0 until jsonArray.length()) {
                            val obj = jsonArray.getJSONObject(i)
                            daftarKota = DaftarKota()
                            daftarKota.id = obj.getInt("id")
                            daftarKota.nama = obj.getString("nama")
                            listDaftarKota.add(daftarKota)
                        }
                        daftarKotaAdapter.notifyDataSetChanged()
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
            })
            task.execute(url)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}