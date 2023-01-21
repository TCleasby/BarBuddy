package com.example.barbuddy

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.*

class SingleFavoritedDrinkFragment(drink: FavoriteDrinkItem) : Fragment() {

    private lateinit var singleFavoritedDrinkRecyclerView: RecyclerView
    private var adapter: DrinkAdapter? = DrinkAdapter(drink)
    private var callbacks: NavCallbacks? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as NavCallbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_single_favorited_drink, container, false)
        singleFavoritedDrinkRecyclerView = view.findViewById(R.id.single_favorited_drink_recycler_view)
        singleFavoritedDrinkRecyclerView.layoutManager = LinearLayoutManager(context)
        singleFavoritedDrinkRecyclerView.adapter = adapter
        ViewCompat.setNestedScrollingEnabled(singleFavoritedDrinkRecyclerView, false)
        return view
    }

    private inner class DrinkHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var drink: FavoriteDrinkItem

        // Drink Misc. Views
        var drinkNameTextView: TextView = itemView.findViewById(R.id.drink_name)
        var drinkInstructionsTextView: TextView = itemView.findViewById(R.id.drink_instructions)
        var drinkImageImageView: ImageView = itemView.findViewById(R.id.drink_image)
        var favoriteDrinkButtonOff: ImageButton =  itemView.findViewById(R.id.favorite_drink_button_off)
        var favoriteDrinkButtonOn: ImageButton = itemView.findViewById(R.id.favorite_drink_button_on)

        // Drink Ingredients TextViews
        var drinkIngredient1TextView: TextView = itemView.findViewById(R.id.drink_ingredient_1)
        var drinkIngredient2TextView: TextView = itemView.findViewById(R.id.drink_ingredient_2)
        var drinkIngredient3TextView: TextView = itemView.findViewById(R.id.drink_ingredient_3)
        var drinkIngredient4TextView: TextView = itemView.findViewById(R.id.drink_ingredient_4)
        var drinkIngredient5TextView: TextView = itemView.findViewById(R.id.drink_ingredient_5)
        var drinkIngredient6TextView: TextView = itemView.findViewById(R.id.drink_ingredient_6)
        var drinkIngredient7TextView: TextView = itemView.findViewById(R.id.drink_ingredient_7)
        var drinkIngredient8TextView: TextView = itemView.findViewById(R.id.drink_ingredient_8)
        var drinkIngredient9TextView: TextView = itemView.findViewById(R.id.drink_ingredient_9)
        var drinkIngredient10TextView: TextView = itemView.findViewById(R.id.drink_ingredient_10)
        var drinkIngredient11TextView: TextView = itemView.findViewById(R.id.drink_ingredient_11)
        var drinkIngredient12TextView: TextView = itemView.findViewById(R.id.drink_ingredient_12)
        var drinkIngredient13TextView: TextView = itemView.findViewById(R.id.drink_ingredient_13)
        var drinkIngredient14TextView: TextView = itemView.findViewById(R.id.drink_ingredient_14)
        var drinkIngredient15TextView: TextView = itemView.findViewById(R.id.drink_ingredient_15)

        // Drink Ingredients Measurements TextViews
        var drinkMeasurement1TextView: TextView = itemView.findViewById(R.id.drink_measurement_1)
        var drinkMeasurement2TextView: TextView = itemView.findViewById(R.id.drink_measurement_2)
        var drinkMeasurement3TextView: TextView = itemView.findViewById(R.id.drink_measurement_3)
        var drinkMeasurement4TextView: TextView = itemView.findViewById(R.id.drink_measurement_4)
        var drinkMeasurement5TextView: TextView = itemView.findViewById(R.id.drink_measurement_5)
        var drinkMeasurement6TextView: TextView = itemView.findViewById(R.id.drink_measurement_6)
        var drinkMeasurement7TextView: TextView = itemView.findViewById(R.id.drink_measurement_7)
        var drinkMeasurement8TextView: TextView = itemView.findViewById(R.id.drink_measurement_8)
        var drinkMeasurement9TextView: TextView = itemView.findViewById(R.id.drink_measurement_9)
        var drinkMeasurement10TextView: TextView = itemView.findViewById(R.id.drink_measurement_10)
        var drinkMeasurement11TextView: TextView = itemView.findViewById(R.id.drink_measurement_11)
        var drinkMeasurement12TextView: TextView = itemView.findViewById(R.id.drink_measurement_12)
        var drinkMeasurement13TextView: TextView = itemView.findViewById(R.id.drink_measurement_13)
        var drinkMeasurement14TextView: TextView = itemView.findViewById(R.id.drink_measurement_14)
        var drinkMeasurement15TextView: TextView = itemView.findViewById(R.id.drink_measurement_15)



        @SuppressLint("ClickableViewAccessibility")
        fun bind(drink: FavoriteDrinkItem){
            this.drink = drink

            Log.d("Drink", this.drink.name.toString())

            // Fill drink info in Text field here //////////////////////////////////////////////////////

            // Drink Misc.
            drinkNameTextView.text = this.drink.name.toString()
            drinkInstructionsTextView.text = this.drink.instructions.toString()
            drinkInstructionsTextView.movementMethod = ScrollingMovementMethod()

            // Favorite Drink Button Functionality ////////////////////
            if(checkIfFavortied("favoriteDrinksNames.txt", this.drink.name.toString())){
                Log.i("Drink:", "Favorited")
                favoriteDrinkButtonOn.visibility = View.VISIBLE
                favoriteDrinkButtonOff.visibility = View.GONE
            } else {
                Log.i("Drink:", "Not Favorited")
                favoriteDrinkButtonOff.visibility = View.VISIBLE
                favoriteDrinkButtonOn.visibility = View.GONE
            }

            //Drink favorited
            favoriteDrinkButtonOff.setOnClickListener{
                favoriteDrinkButtonOn.visibility = View.VISIBLE
                favoriteDrinkButtonOff.visibility = View.GONE
                val drinkObjectAsByteArray = Json.encodeToString(drink).toByteArray()
                val drinkObjectNameAsByteArray = Json.encodeToString(drink.name).toByteArray()
                writeToFile("favoriteDrinks.txt", drinkObjectAsByteArray)
                writeToFile("favoriteDrinksNames.txt", drinkObjectNameAsByteArray)
            }

            //Drink unfavorited
            favoriteDrinkButtonOn.setOnClickListener{
                favoriteDrinkButtonOff.visibility = View.VISIBLE
                favoriteDrinkButtonOn.visibility = View.GONE
                val drinkObjectAsJson = Json.encodeToString(drink)
                Log.i("drinkObjectAsJson:", drinkObjectAsJson)
                deleteFromFile("favoriteDrinksNames.txt", "\"" + drink.name.toString() + "\"")
                deleteFromFile("favoriteDrinks.txt", drinkObjectAsJson)
            }

            drinkInstructionsTextView.setOnTouchListener { v, _ ->
                v?.parent?.requestDisallowInterceptTouchEvent(true)
                false
            }

            //checkDataIsNull("", drinkInstructionsTextView)

            getImageFromURL(drink.thumbnail.toString(),drinkImageImageView)

            // Drink Ingredients
            checkDataIsNull(this.drink.ingredient1.toString(), drinkIngredient1TextView)
            checkDataIsNull(this.drink.ingredient2.toString(), drinkIngredient2TextView)
            checkDataIsNull(this.drink.ingredient3.toString(), drinkIngredient3TextView)
            checkDataIsNull(this.drink.ingredient4.toString(), drinkIngredient4TextView)
            checkDataIsNull(this.drink.ingredient5.toString(), drinkIngredient5TextView)
            checkDataIsNull(this.drink.ingredient6.toString(), drinkIngredient6TextView)
            checkDataIsNull(this.drink.ingredient7.toString(), drinkIngredient7TextView)
            checkDataIsNull(this.drink.ingredient8.toString(), drinkIngredient8TextView)
            checkDataIsNull(this.drink.ingredient9.toString(), drinkIngredient9TextView)
            checkDataIsNull(this.drink.ingredient10.toString(), drinkIngredient10TextView)
            checkDataIsNull(this.drink.ingredient11.toString(), drinkIngredient11TextView)
            checkDataIsNull(this.drink.ingredient12.toString(), drinkIngredient12TextView)
            checkDataIsNull(this.drink.ingredient13.toString(), drinkIngredient13TextView)
            checkDataIsNull(this.drink.ingredient14.toString(), drinkIngredient14TextView)
            checkDataIsNull(this.drink.ingredient15.toString(), drinkIngredient15TextView)

            // Drink Ingredients Measurements
            checkDataIsNull(this.drink.measurement1.toString(), drinkMeasurement1TextView)
            checkDataIsNull(this.drink.measurement2.toString(), drinkMeasurement2TextView)
            checkDataIsNull(this.drink.measurement3.toString(), drinkMeasurement3TextView)
            checkDataIsNull(this.drink.measurement4.toString(), drinkMeasurement4TextView)
            checkDataIsNull(this.drink.measurement5.toString(), drinkMeasurement5TextView)
            checkDataIsNull(this.drink.measurement6.toString(), drinkMeasurement6TextView)
            checkDataIsNull(this.drink.measurement7.toString(), drinkMeasurement7TextView)
            checkDataIsNull(this.drink.measurement8.toString(), drinkMeasurement8TextView)
            checkDataIsNull(this.drink.measurement9.toString(), drinkMeasurement9TextView)
            checkDataIsNull(this.drink.measurement10.toString(), drinkMeasurement10TextView)
            checkDataIsNull(this.drink.measurement11.toString(), drinkMeasurement11TextView)
            checkDataIsNull(this.drink.measurement12.toString(), drinkMeasurement12TextView)
            checkDataIsNull(this.drink.measurement13.toString(), drinkMeasurement13TextView)
            checkDataIsNull(this.drink.measurement14.toString(), drinkMeasurement14TextView)
            checkDataIsNull(this.drink.measurement15.toString(), drinkMeasurement15TextView)

        }

    }

    private inner class DrinkAdapter(var drinks: FavoriteDrinkItem) : RecyclerView.Adapter<DrinkHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkHolder {
            val view = layoutInflater.inflate(R.layout.list_item_drink, parent, false)
            return DrinkHolder(view)
        }

        override fun onBindViewHolder(holder: DrinkHolder, position: Int) {
            val drink = drinks
            holder.bind(drink)
        }

        override fun getItemCount(): Int {
            return 1
        }

    }

    private fun getImageFromURL(url: String, imageView: ImageView) {
        Picasso.get().load(url).into(imageView)
    }

    private fun checkDataIsNull(string: String, textView: TextView) {
        if (string == "null" || string == "") {
            textView.visibility = View.GONE
        } else {
            textView.text = string
        }

    }

    private fun deleteFromFile(fileName: String, stringToDelete: String){

        //File Setup
        val path = context!!.filesDir
        val directory = File(path, "LET")
        directory.mkdirs()
        val inputFile = File(directory, fileName)
        val tempFile = File(directory,"myTempFile.txt")

        //Reader and Writer
        val reader = BufferedReader(FileReader(inputFile))
        val writer = BufferedWriter(FileWriter(tempFile))

        //Loop through file and delete current drink
        reader.forEachLine {
            // trim newline when comparing with lineToRemove
            val trimmedLine = it
            if (trimmedLine == stringToDelete){
                Log.i("Line Deleted:", trimmedLine)
                Log.i("Line Deleted:", "^^^DELETED^^^")
            } else {
                Log.i("Line Saved:", trimmedLine)
                writer.write(it + System.getProperty("line.separator"))
            }
        }
        tempFile.renameTo(inputFile)
        writer.close()
        reader.close()
    }

    private fun writeToFile(fileName: String, byteArray: ByteArray){

        val lineSeparator: String = System.getProperty("line.separator") as String

        // File
        val path = context!!.filesDir
        val directory = File(path, "LET")
        directory.mkdirs()
        val file = File(directory, fileName)

        //append drink to file
        FileOutputStream(file, true).use {
            it.write(byteArray)
            it.write(lineSeparator.toByteArray())
        }

        Log.i("Item Favorited:", "Item saved to $file")
    }

    private fun checkIfFavortied(fileName: String, drinkName: String): Boolean {

        Log.d("CheckIfFavorited:", "Inside")
        var sameDrink: Boolean = false

        //File Setup
        val path = context!!.filesDir
        val directory = File(path, "LET")
        directory.mkdirs()
        val inputFile = File(directory, fileName)

        //Reader
        val reader = BufferedReader(FileReader(inputFile))

        //Loop through file and compare names
        reader.forEachLine {
            Log.i(it.toString(), drinkName)
            if (it == "\"$drinkName\"") {
                sameDrink = true
            }
        }
        return sameDrink
    }

}