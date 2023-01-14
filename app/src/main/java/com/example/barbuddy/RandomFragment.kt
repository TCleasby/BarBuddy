package com.example.barbuddy

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.core.view.ViewCompat.setNestedScrollingEnabled
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso


class RandomFragment : Fragment() {

    private lateinit var drinkRecyclerView: RecyclerView
    private lateinit var drinkViewModel: DrinkViewModel
    private lateinit var drinkViewModelFactory: DrinkViewModelFactory
    private var adapter: DrinkAdapter? = DrinkAdapter(emptyList())
    private lateinit var rndDrinkButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        drinkViewModelFactory = DrinkViewModelFactory()
        drinkViewModel = ViewModelProvider(this, drinkViewModelFactory).get(DrinkViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_random, container, false)
        drinkRecyclerView = view.findViewById(R.id.drink_recycler_view)
        drinkRecyclerView.layoutManager = LinearLayoutManager(context)
        drinkRecyclerView.adapter = adapter
        setNestedScrollingEnabled(drinkRecyclerView, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        drinkViewModel.drinkItemLiveData.observe(
            viewLifecycleOwner,
            Observer { drinks ->
                drinkRecyclerView.adapter = DrinkAdapter(drinks)
                Log.d("Observer", "Received Results")
            }
        )

        // Button Functionality
        rndDrinkButton = view.findViewById(R.id.rndDrinkBtn)
        rndDrinkButton.setOnClickListener{
            resetAdapterState()
        }
    }


    private inner class DrinkHolder(view: View) : RecyclerView.ViewHolder(view) {
        private lateinit var drink: DrinkItem

        // Drink Misc. Views
        var drinkNameTextView: TextView = itemView.findViewById(R.id.drink_name)
        var drinkInstructionsTextView: TextView = itemView.findViewById(R.id.drink_instructions)
        var drinkInstructionsScrollView: ScrollView =  itemView.findViewById(R.id.drink_instructions_scrollview)
        var drinkImageImageView: ImageView = itemView.findViewById(R.id.drink_image)

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



        fun bind(drink: DrinkItem){
            this.drink = drink

            if (checkForAlcohol(this.drink)){
                resetAdapterState()
            }

            Log.d("Drink", this.drink.name.toString())

            // Fill drink info in Text field here //////////////////////////////////////////////////////

            // Drink Misc.
            drinkNameTextView.text = this.drink.name.toString()
            drinkInstructionsTextView.text = this.drink.instructions.toString()

            drinkInstructionsTextView.movementMethod = ScrollingMovementMethod()

//            drinkRecyclerView.setOnTouchListener(object : View.OnTouchListener {
//                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
//                    drinkInstructionsScrollView.parent.requestDisallowInterceptTouchEvent(false);
//                    return false;
//                }
//            })

            drinkInstructionsTextView.setOnTouchListener(object : View.OnTouchListener {
                override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                    v?.parent?.requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            })

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

    private inner class DrinkAdapter(var drinks: List<DrinkItem>) : RecyclerView.Adapter<DrinkHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkHolder {
            val view = layoutInflater.inflate(R.layout.list_item_drink, parent, false)
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

    private fun checkDataIsNull(string: String, textView: TextView) {
        if (string == "null" || string == "") {
            textView.visibility = View.GONE
        } else {
            textView.text = string
        }

    }

    private fun getImageFromURL(url: String, imageView: ImageView) {
        Picasso.get().load(url).into(imageView)
    }

    private fun  checkForAlcohol(drink: DrinkItem): Boolean {
        Log.d(drink.alcoholic.toString(), "Alcohol")
        if (drink.alcoholic == "Alcoholic") {
            return false
        }
        return true
    }

    private fun resetAdapterState() {
        drinkViewModel.getNewDrink()
        var adapter: DrinkAdapter? = DrinkAdapter(emptyList())
        drinkRecyclerView.adapter = adapter
        drinkViewModel.drinkItemLiveData.observe(
            viewLifecycleOwner,
            Observer { drinks ->
                drinkRecyclerView.adapter = DrinkAdapter(drinks)
                Log.d("Observer", "Received Results")
            }
        )
    }
}

