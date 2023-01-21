package com.example.barbuddy


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat.setNestedScrollingEnabled
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import java.io.*


class FavoritedDrinksFragment : Fragment() {

    private lateinit var favoritedDrinkRecyclerView: RecyclerView
    private var adapter: DrinkAdapter? = DrinkAdapter(emptyList())
    private var callbacks: NavCallbacks? =null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as NavCallbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favorited_drinks, container, false)
        favoritedDrinkRecyclerView = view.findViewById(R.id.favorited_drink_recycler_view)
        favoritedDrinkRecyclerView.layoutManager = LinearLayoutManager(context)
        favoritedDrinkRecyclerView.adapter = adapter
        setNestedScrollingEnabled(favoritedDrinkRecyclerView, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Log.d("FetchDrinksFromFile:", "Started")
        var drinks: List<FavoriteDrinkItem> = fetchDrinksFromFile("favoriteDrinks.txt")
        favoritedDrinkRecyclerView.adapter = DrinkAdapter(drinks)
        Log.d("Observer", "Received Results")
    }

    private fun fetchDrinksFromFile(fileName: String): List<FavoriteDrinkItem> {

        Log.d("fetchDrinksFromFile", "Inside")

        var drinkList = mutableListOf<FavoriteDrinkItem>()

        //File Setup
        val path = context!!.filesDir
        val directory = File(path, "LET")
        directory.mkdirs()
        val inputFile = File(directory, fileName)

        //Reader
        val reader = BufferedReader(FileReader(inputFile))

        //Loop through file and build list
        reader.forEachLine {
            Log.i("it:", it.toString())
            val drinkItem = Gson().fromJson(it, FavoriteDrinkItem::class.java)
            Log.i("drinkItem:", drinkItem.name.toString())
            drinkList.add(drinkItem)

        }
        reader.close()
        Log.i("drinkList:", drinkList.toString())
        return drinkList
    }

    inner class DrinkHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var drink: FavoriteDrinkItem

        var drinkNameTextView: TextView = itemView.findViewById(R.id.drink_name)
        var drinkImageImageView: ImageView = itemView.findViewById(R.id.drink_image)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(drink: FavoriteDrinkItem){
            this.drink = drink

            drinkNameTextView.text = this.drink.name.toString()

            getImageFromURL(drink.thumbnail.toString(),drinkImageImageView)
        }

        override fun onClick(v: View?) {
            Log.i("Item Clicked:", this.drink.name.toString())
            callbacks?.onDrinkSelected(this.drink)
        }
    }

    private inner class DrinkAdapter(var drinks: List<FavoriteDrinkItem>) : RecyclerView.Adapter<DrinkHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkHolder {
            val view = layoutInflater.inflate(R.layout.list_item_favorited_drink, parent, false)
            return DrinkHolder(view)
        }

        override fun getItemCount(): Int {
            return drinks.size
        }

        override fun onBindViewHolder(holder: DrinkHolder, position: Int) {
            val drink = drinks[position]
            holder.bind(drink)
        }
    }

    private fun getImageFromURL(url: String, imageView: ImageView) {
        Picasso.get().load(url).into(imageView)
    }

}

