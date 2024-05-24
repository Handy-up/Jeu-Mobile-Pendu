import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.handy.projetfinalenativelependu.Accueil
import com.handy.projetfinalenativelependu.DictionaryView
import com.handy.projetfinalenativelependu.R
import com.handy.projetfinalenativelependu.UpdateActivity

class MotsAdapter(private val activity: Activity, private val context: Context,
                  private val idMot: ArrayList<String>, private val motFrancais: ArrayList<String>,
                  private val motAnglais: ArrayList<String>, private val difficulte: ArrayList<String>) :
    RecyclerView.Adapter<MotsAdapter.MyViewHolder>() {

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.activity_mot_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(@NonNull holder: MyViewHolder, position: Int) {
        Log.d("MotsAdapter", "Position : $position")
        holder.idMotTV.text = idMot[position]
        holder.motFrancaisTV.text = motFrancais[position]
        holder.motAnglaisTV.text = motAnglais[position]
        holder.difficulte.text = difficulte[position]

        //RecyclerView onClickListener
        holder.mainLayout.setOnClickListener {
            val intent = Intent(context, UpdateActivity::class.java)
            intent.putExtra("idMot", idMot[position])
            intent.putExtra("motFrancais", motFrancais[position])
            intent.putExtra("motAnglais", motAnglais[position])
            intent.putExtra("difficulte", difficulte[position])
            activity.startActivityForResult(intent, 1)
        }
    }



    override fun getItemCount(): Int {
        return idMot.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val idMotTV: TextView = itemView.findViewById(R.id.idMotTV)
        val motFrancaisTV: TextView = itemView.findViewById(R.id.motFrancaisTV)
        val motAnglaisTV: TextView = itemView.findViewById(R.id.motAnglaisTV)
        val difficulte: TextView = itemView.findViewById(R.id.difficulteTV)
        val mainLayout: LinearLayout = itemView.findViewById(R.id.mainLayout)

        init {
            //Animate Recyclerview
            val translateAnim: Animation = AnimationUtils.loadAnimation(context, R.anim.translate_anim)
            mainLayout.startAnimation(translateAnim)
        }
    }
}
