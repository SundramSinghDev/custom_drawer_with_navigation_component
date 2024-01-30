package com.magentotwo.navigation_drawer_example

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.magentotwo.navigation_drawer_example.databinding.MagenativeListItemBinding
import com.magentotwo.navigation_drawer_example.databinding.SideMenuCateforySingleItemViewBinding
import com.magentotwo.navigation_drawer_example.databinding.SideMenuCategoryViewBinding
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
class SideMenuCategoryView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {
    private var layoutView: SideMenuCategoryViewBinding

    init {
        layoutView = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.side_menu_category_view,
            this,
            true
        )
//        createSideDrawerSpecialCategories()
        createSideDrawerCategories()
    }


    private fun createSideDrawerCategories() {
        try {
            val jsonObjectRes = JSONArray(
                """
                [{"status":"success","data":{"categories":[{"main_category_id":"3","main_category_name":"Gear","main_category_image":false,"has_child":true,"sub_cats":[{"sub_category_id":"4","sub_category_name":"Bags","sub_category_image":"https:\/\/livedemo-81.cedcommerce.com\/buyerapp\/pub\/media\/catalog\/category\/2.png","has_child":false},{"sub_category_id":"5","sub_category_name":"Fitness
                 Equipment","sub_category_image":"https:\/\/livedemo-81.cedcommerce.com\/buyerapp\/pub\/media\/catalog\/category\/imgpsh_fullsize_anim_1__2.png","has_child":false},{"sub_category_id":"6","sub_category_name":"Watches","sub_category_image":"https:\/\/livedemo-81.cedcommerce.com\/buyerapp\/pub\/media\/catalog\/category\/imgpsh_fullsize_anim_2__2.png","has_child":false}]},{"main_category_id":"9","main_category_name":"Training","main_category_image":false,"has_child":true,"sub_cats":[{"sub_category_id":"10","sub_category_name":"Video
                 Download","sub_category_image":false,"has_child":false}]},{"main_category_id":"11","main_category_name":"Men","main_category_image":false,"has_child":true,"sub_cats":[{"sub_category_id":"12","sub_category_name":"Tops","sub_category_image":false,"has_child":true},{"sub_category_id":"13","sub_category_name":"Bottoms","sub_category_image":false,"has_child":true}]},{"main_category_id":"20","main_category_name":"Women","main_category_image":false,"has_child":true,"sub_cats":[{"sub_category_id":"21","sub_category_name":"Tops","sub_category_image":false,"has_child":true},{"sub_category_id":"22","sub_category_name":"Bottoms","sub_category_image":false,"has_child":true},{"sub_category_id":"41","sub_category_name":"Women
                 Special","sub_category_image":false,"has_child":false}]},{"main_category_id":"37","main_category_name":"Sale","main_category_image":false,"has_child":false},{"main_category_id":"38","main_category_name":"What's
                 New","main_category_image":"https:\/\/livedemo-81.cedcommerce.com\/buyerapp\/pub\/media\/catalog\/category\/Category-3_1.jpeg","has_child":false},{"main_category_id":"42","main_category_name":"Brands","main_category_image":false,"has_child":true,"sub_cats":[{"sub_category_id":"43","sub_category_name":"Puma","sub_category_image":false,"has_child":false},{"sub_category_id":"44","sub_category_name":"Nike","sub_category_image":false,"has_child":false},{"sub_category_id":"45","sub_category_name":"Adidas","sub_category_image":"https:\/\/livedemo-81.cedcommerce.com\/buyerapp\/pub\/media\/catalog\/category\/3_1.png","has_child":false},{"sub_category_id":"46","sub_category_name":"H&M","sub_category_image":"https:\/\/livedemo-81.cedcommerce.com\/buyerapp\/pub\/media\/catalog\/category\/4_1.png","has_child":false},{"sub_category_id":"47","sub_category_name":"Vero
                 Moda","sub_category_image":"https:\/\/livedemo-81.cedcommerce.com\/buyerapp\/pub\/media\/catalog\/category\/5_1.png","has_child":false},{"sub_category_id":"48","sub_category_name":"Vans","sub_category_image":"https:\/\/livedemo-81.cedcommerce.com\/buyerapp\/pub\/media\/catalog\/category\/6_1.png","has_child":false}]}],"special_categories":[]}}]
            """.trimIndent()
            )

            val categoties =
                jsonObjectRes.getJSONObject(0).getJSONObject("data").getJSONArray("categories")

            for (i in 0 until categoties.length()) {
                val cat: JSONObject = categoties.getJSONObject(i)
                val categorySectionBinding: SideMenuCateforySingleItemViewBinding =
                    DataBindingUtil.inflate(
                        LayoutInflater.from(context),
                        R.layout.side_menu_catefory_single_item_view,
                        layoutView.sideMenuCateCustomViewParent,
                        false
                    )
                val maincatlayout: LinearLayoutCompat = categorySectionBinding.MageNativeMaincat
                val lblListHeader: TextView = categorySectionBinding.MageNativeLblListHeader
                val maincatid: TextView = categorySectionBinding.MageNativeMaincatid
                val expandxlose: ImageView = categorySectionBinding.MageNativeExpandxlose
                if (cat.has("main_category_name")) {
                    lblListHeader.text = cat.getString("main_category_name")
                }
                if (cat.has("main_category_id")) {
                    maincatid.text = cat.getString("main_category_id")
                }
                if (cat.has("main_category_image")) {
                    Glide.with(context)
                        .load(cat.getString("main_category_image"))
                        .into(categorySectionBinding.catIcon)
                }
                if (cat.getBoolean("has_child")) {
                    expandxlose.visibility = VISIBLE
                }
                maincatlayout.setOnClickListener { v ->
                    val maincatrelative = maincatlayout.getChildAt(0) as RelativeLayout
                    val subcategorysection =
                        maincatlayout.getChildAt(1) as LinearLayoutCompat
                    val maincid = maincatrelative.getChildAt(5) as TextView
                    val rotateimage =
                        maincatrelative.getChildAt(4) as ImageView
                    try {
                        if (cat.has("sub_cats")) {
                            if (cat.getJSONArray("sub_cats").length() > 0) {
                                if (subcategorysection.childCount == 0) {
                                    val sub_cats = cat.getJSONArray("sub_cats")
                                    var countersub = 0
                                    for (j in 0 until sub_cats.length()) {
                                        val subcategory = sub_cats.getJSONObject(j)
                                        countersub += 1
                                        val listItemBinding =
                                            DataBindingUtil.inflate(
                                                LayoutInflater.from(context),
                                                R.layout.magenative_list_item,
                                                subcategorysection,
                                                true
                                            ) as MagenativeListItemBinding
                                        val subrelat =
                                            listItemBinding.MageNativeSubrelat
                                        subrelat.setOnClickListener { v1: View? ->
                                            val subcatid = subrelat.getChildAt(2) as TextView
//                                            jumpToProductListing(
//                                                subcatid.text.toString().trim { it <= ' ' })
//                                            com.magentotwo.magenativeapp.base.fragment.Ced_FragmentDrawer.mDrawerLayout.closeDrawers()
                                        }
                                        val upperline: TextView =
                                            listItemBinding.MageNativeUpperline
                                        val bottomline: TextView =
                                            listItemBinding.MageNativeBottomline
                                        val lblListItem: TextView =
                                            listItemBinding.MageNativeLblListItem
                                        val lblListItemid: TextView =
                                            listItemBinding.MageNativeLblListItemid
                                        lblListItem.text =
                                            subcategory.getString("sub_category_name")
                                        lblListItemid.text =
                                            subcategory.getString("sub_category_id")
                                        if (sub_cats.length() == 1) {
                                            upperline.visibility = INVISIBLE
                                            bottomline.visibility = INVISIBLE
                                        } else {
                                            if (countersub == 1) {
                                                upperline.visibility = INVISIBLE
                                            } else {
                                                if (countersub == sub_cats.length()) {
                                                    bottomline.visibility = INVISIBLE
                                                }
                                            }
                                        }
//                                        subcategorysection.addView(listItemBinding.root)
                                    }
                                    val sampleFadeAnimation =
                                        AnimationUtils.loadAnimation(
                                            context,
                                            R.anim.magenative_rotate
                                        )
                                    sampleFadeAnimation.repeatCount = 1
                                    sampleFadeAnimation.fillAfter = true
                                    rotateimage.startAnimation(sampleFadeAnimation)
                                } else {
                                    val sampleFadeAnimation =
                                        AnimationUtils.loadAnimation(
                                            context,
                                            R.anim.magenative_rotatereverse
                                        )
                                    sampleFadeAnimation.repeatCount = 1
                                    sampleFadeAnimation.fillAfter = true
                                    rotateimage.startAnimation(sampleFadeAnimation)
                                    subcategorysection.removeAllViews()
                                }
                            }
                        } else {
//                            jumpToProductListing(maincid.text.toString().trim { it <= ' ' })
//                            com.magentotwo.magenativeapp.base.fragment.Ced_FragmentDrawer.mDrawerLayout.closeDrawers()
                        }
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }
                if (cat.has("main_category_name") && cat.getString("main_category_name") != "null") layoutView.sideMenuCateCustomViewParent.addView(
                    categorySectionBinding.root
                )
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }


    private fun createSideDrawerSpecialCategories() {
        //----------------specialcategory
        try {
            val special_categories_jsonObject = JSONArray(
                "[\n" +
                        "    {\n" +
                        "        \"status\": \"success\",\n" +
                        "        \"data\": {\n" +
                        "            \"categories\": [\n" +
                        "                {\n" +
                        "                    \"main_category_id\": \"3\",\n" +
                        "                    \"main_category_name\": \"Gear\",\n" +
                        "                    \"main_category_image\": \"https://livedemo-81.cedcommerce.com/advance/pub/media/catalog/category/gear_1.jpg\",\n" +
                        "                    \"has_child\": true,\n" +
                        "                    \"sub_cats\": [\n" +
                        "                        {\n" +
                        "                            \"sub_category_id\": \"4\",\n" +
                        "                            \"sub_category_name\": \"Bags\",\n" +
                        "                            \"sub_category_image\": false,\n" +
                        "                            \"has_child\": false\n" +
                        "                        },\n" +
                        "                        {\n" +
                        "                            \"sub_category_id\": \"5\",\n" +
                        "                            \"sub_category_name\": \"Fitness Equipment\",\n" +
                        "                            \"sub_category_image\": false,\n" +
                        "                            \"has_child\": false\n" +
                        "                        },\n" +
                        "                        {\n" +
                        "                            \"sub_category_id\": \"6\",\n" +
                        "                            \"sub_category_name\": \"Watches\",\n" +
                        "                            \"sub_category_image\": false,\n" +
                        "                            \"has_child\": false\n" +
                        "                        }\n" +
                        "                    ]\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"main_category_id\": \"9\",\n" +
                        "                    \"main_category_name\": \"Training\",\n" +
                        "                    \"main_category_image\": false,\n" +
                        "                    \"has_child\": true,\n" +
                        "                    \"sub_cats\": [\n" +
                        "                        {\n" +
                        "                            \"sub_category_id\": \"10\",\n" +
                        "                            \"sub_category_name\": \"Video Download\",\n" +
                        "                            \"sub_category_image\": false,\n" +
                        "                            \"has_child\": false\n" +
                        "                        }\n" +
                        "                    ]\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"main_category_id\": \"11\",\n" +
                        "                    \"main_category_name\": \"Men\",\n" +
                        "                    \"main_category_image\": \"https://livedemo-81.cedcommerce.com/advance/pub/media/catalog/category/men-removebg-preview_1.png\",\n" +
                        "                    \"has_child\": true,\n" +
                        "                    \"sub_cats\": [\n" +
                        "                        {\n" +
                        "                            \"sub_category_id\": \"12\",\n" +
                        "                            \"sub_category_name\": \"Tops\",\n" +
                        "                            \"sub_category_image\": false,\n" +
                        "                            \"has_child\": true\n" +
                        "                        },\n" +
                        "                        {\n" +
                        "                            \"sub_category_id\": \"13\",\n" +
                        "                            \"sub_category_name\": \"Bottoms\",\n" +
                        "                            \"sub_category_image\": false,\n" +
                        "                            \"has_child\": true\n" +
                        "                        }\n" +
                        "                    ]\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"main_category_id\": \"20\",\n" +
                        "                    \"main_category_name\": \"Women\",\n" +
                        "                    \"main_category_image\": \"https://livedemo-81.cedcommerce.com/advance/pub/media/catalog/category/women_1.png\",\n" +
                        "                    \"has_child\": true,\n" +
                        "                    \"sub_cats\": [\n" +
                        "                        {\n" +
                        "                            \"sub_category_id\": \"21\",\n" +
                        "                            \"sub_category_name\": \"Tops\",\n" +
                        "                            \"sub_category_image\": false,\n" +
                        "                            \"has_child\": true\n" +
                        "                        },\n" +
                        "                        {\n" +
                        "                            \"sub_category_id\": \"22\",\n" +
                        "                            \"sub_category_name\": \"Bottoms\",\n" +
                        "                            \"sub_category_image\": false,\n" +
                        "                            \"has_child\": true\n" +
                        "                        },\n" +
                        "                        {\n" +
                        "                            \"sub_category_id\": \"47\",\n" +
                        "                            \"sub_category_name\": \"Accessory Hair\",\n" +
                        "                            \"sub_category_image\": false,\n" +
                        "                            \"has_child\": false\n" +
                        "                        }\n" +
                        "                    ]\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"main_category_id\": \"37\",\n" +
                        "                    \"main_category_name\": \"Sale\",\n" +
                        "                    \"main_category_image\": \"https://livedemo-81.cedcommerce.com/advance/pub/media/catalog/category/sale_1.jpg\",\n" +
                        "                    \"has_child\": false\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"main_category_id\": \"38\",\n" +
                        "                    \"main_category_name\": \"What's New\",\n" +
                        "                    \"main_category_image\": \"https://livedemo-81.cedcommerce.com/advance/pub/media/catalog/category/PEACH.jpg\",\n" +
                        "                    \"has_child\": false\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"main_category_id\": \"41\",\n" +
                        "                    \"main_category_name\": \"Tires\",\n" +
                        "                    \"main_category_image\": false,\n" +
                        "                    \"has_child\": true,\n" +
                        "                    \"sub_cats\": [\n" +
                        "                        {\n" +
                        "                            \"sub_category_id\": \"42\",\n" +
                        "                            \"sub_category_name\": \"Off Road\",\n" +
                        "                            \"sub_category_image\": false,\n" +
                        "                            \"has_child\": false\n" +
                        "                        },\n" +
                        "                        {\n" +
                        "                            \"sub_category_id\": \"43\",\n" +
                        "                            \"sub_category_name\": \"Rib Tires\",\n" +
                        "                            \"sub_category_image\": false,\n" +
                        "                            \"has_child\": false\n" +
                        "                        },\n" +
                        "                        {\n" +
                        "                            \"sub_category_id\": \"44\",\n" +
                        "                            \"sub_category_name\": \"All-terrain tires\",\n" +
                        "                            \"sub_category_image\": false,\n" +
                        "                            \"has_child\": false\n" +
                        "                        }\n" +
                        "                    ]\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"main_category_id\": \"46\",\n" +
                        "                    \"main_category_name\": \"UAE\",\n" +
                        "                    \"main_category_image\": false,\n" +
                        "                    \"has_child\": false\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"main_category_id\": \"51\",\n" +
                        "                    \"main_category_name\": \"SUbLinks\",\n" +
                        "                    \"main_category_image\": false,\n" +
                        "                    \"has_child\": true,\n" +
                        "                    \"sub_cats\": [\n" +
                        "                        {\n" +
                        "                            \"sub_category_id\": \"52\",\n" +
                        "                            \"sub_category_name\": \"cat1\",\n" +
                        "                            \"sub_category_image\": false,\n" +
                        "                            \"has_child\": false\n" +
                        "                        }\n" +
                        "                    ]\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"main_category_id\": \"53\",\n" +
                        "                    \"main_category_name\": \"FERTILIZER\",\n" +
                        "                    \"main_category_image\": false,\n" +
                        "                    \"has_child\": true,\n" +
                        "                    \"sub_cats\": [\n" +
                        "                        {\n" +
                        "                            \"sub_category_id\": \"54\",\n" +
                        "                            \"sub_category_name\": \"STRAIGHT FERTILIZER\",\n" +
                        "                            \"sub_category_image\": false,\n" +
                        "                            \"has_child\": false\n" +
                        "                        },\n" +
                        "                        {\n" +
                        "                            \"sub_category_id\": \"55\",\n" +
                        "                            \"sub_category_name\": \"SPECIALTY FERTILIZER\",\n" +
                        "                            \"sub_category_image\": false,\n" +
                        "                            \"has_child\": false\n" +
                        "                        }\n" +
                        "                    ]\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"main_category_id\": \"56\",\n" +
                        "                    \"main_category_name\": \"AGRO CHEMICALS\",\n" +
                        "                    \"main_category_image\": false,\n" +
                        "                    \"has_child\": true,\n" +
                        "                    \"sub_cats\": [\n" +
                        "                        {\n" +
                        "                            \"sub_category_id\": \"57\",\n" +
                        "                            \"sub_category_name\": \"FUMIGANT\",\n" +
                        "                            \"sub_category_image\": false,\n" +
                        "                            \"has_child\": false\n" +
                        "                        },\n" +
                        "                        {\n" +
                        "                            \"sub_category_id\": \"58\",\n" +
                        "                            \"sub_category_name\": \"FUNGICIDE\",\n" +
                        "                            \"sub_category_image\": false,\n" +
                        "                            \"has_child\": false\n" +
                        "                        }\n" +
                        "                    ]\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"main_category_id\": \"59\",\n" +
                        "                    \"main_category_name\": \"SEEDS\",\n" +
                        "                    \"main_category_image\": false,\n" +
                        "                    \"has_child\": true,\n" +
                        "                    \"sub_cats\": [\n" +
                        "                        {\n" +
                        "                            \"sub_category_id\": \"60\",\n" +
                        "                            \"sub_category_name\": \"Maize Seed\",\n" +
                        "                            \"sub_category_image\": false,\n" +
                        "                            \"has_child\": false\n" +
                        "                        },\n" +
                        "                        {\n" +
                        "                            \"sub_category_id\": \"61\",\n" +
                        "                            \"sub_category_name\": \"Vegetable Seed\",\n" +
                        "                            \"sub_category_image\": false,\n" +
                        "                            \"has_child\": false\n" +
                        "                        }\n" +
                        "                    ]\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"main_category_id\": \"62\",\n" +
                        "                    \"main_category_name\": \"AGRI INPUTS-EIHL\",\n" +
                        "                    \"main_category_image\": false,\n" +
                        "                    \"has_child\": true,\n" +
                        "                    \"sub_cats\": [\n" +
                        "                        {\n" +
                        "                            \"sub_category_id\": \"63\",\n" +
                        "                            \"sub_category_name\": \"IRRIGATION\",\n" +
                        "                            \"sub_category_image\": false,\n" +
                        "                            \"has_child\": false\n" +
                        "                        },\n" +
                        "                        {\n" +
                        "                            \"sub_category_id\": \"64\",\n" +
                        "                            \"sub_category_name\": \"TOOLS & EQUIPMENTS\",\n" +
                        "                            \"sub_category_image\": false,\n" +
                        "                            \"has_child\": false\n" +
                        "                        }\n" +
                        "                    ]\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"main_category_id\": \"66\",\n" +
                        "                    \"main_category_name\": \"native\",\n" +
                        "                    \"main_category_image\": false,\n" +
                        "                    \"has_child\": false\n" +
                        "                }\n" +
                        "            ],\n" +
                        "            \"special_categories\": []\n" +
                        "        }\n" +
                        "    }\n" +
                        "]"

            )
            for (i in 0 until special_categories_jsonObject
                .length()) {
                val cat: JSONObject = special_categories_jsonObject.getJSONObject(i)
                val categorySectionBinding: SideMenuCateforySingleItemViewBinding =
                    DataBindingUtil.inflate(
                        LayoutInflater.from(context),
                        R.layout.side_menu_catefory_single_item_view,
                        layoutView.sideMenuCateCustomViewParent,
                        false
                    )
                val maincatlayout: LinearLayoutCompat = categorySectionBinding.MageNativeMaincat
                val lblListHeader: TextView = categorySectionBinding.MageNativeLblListHeader
                val maincatid: TextView = categorySectionBinding.MageNativeMaincatid
                val expandxlose: ImageView = categorySectionBinding.MageNativeExpandxlose
                if (cat.has("special_category_name")) {
                    lblListHeader.text = cat.getString("special_category_name")
                    lblListHeader.setTextColor(ContextCompat.getColor(context, R.color.black))
                }
                if (cat.has("special_category_id")) {
                    maincatid.text = cat.getString("special_category_id")
                }
                if (cat.has("special_category_image")) {
                    Glide.with(context)
                        .load(cat.getString("special_category_image"))
                        .into(categorySectionBinding.catIcon)
                }
                if (cat.getBoolean("has_child") && cat.has("sub_cats")) {
                    expandxlose.visibility = VISIBLE
                }
                /*                maincatlayout.setOnClickListener { v ->
                                    val maincatrelative = maincatlayout.getChildAt(0) as RelativeLayout
                                    val subcategorysection: CustomLinearLayout =
                                        maincatlayout.getChildAt(1) as CustomLinearLayout
                                    //                    TextView maincatname = (TextView) maincatrelative.getChildAt(0);
                                    val maincid = maincatrelative.getChildAt(4) as TextView
                                    val rotateimage =
                                        maincatrelative.getChildAt(3) as ImageView
                                    try {
                                        if (cat.has("sub_cats")) {
                                            if (cat.getJSONArray("sub_cats").length() > 0) {
                                                if (subcategorysection.getChildCount() === 0) {
                                                    val sub_cats = cat.getJSONArray("sub_cats")
                                                    var countersub = 0
                                                    for (j in 0 until sub_cats.length()) {
                                                        val subcategory = sub_cats.getJSONObject(j)
                                                        countersub = countersub + 1
                                                        val listItemBinding: MagenativeListItemBinding =
                                                            DataBindingUtil.inflate(
                                                                getLayoutInflater(),
                                                                R.layout.magenative_list_item,
                                                                null,
                                                                false
                                                            )
                                                        val subrelat: RelativeLayout =
                                                            listItemBinding.MageNativeSubrelat
                                                        subrelat.setOnClickListener { v1: View? ->
                                                            val subcatid = subrelat.getChildAt(2) as TextView
                                                            jumpToProductListing(
                                                                subcatid.text.toString().trim { it <= ' ' })
                                                            com.magentotwo.magenativeapp.base.fragment.Ced_FragmentDrawer.mDrawerLayout.closeDrawers()
                                                        }
                                                        val upperline: TextView =
                                                            listItemBinding.MageNativeUpperline
                                                        val bottomline: TextView =
                                                            listItemBinding.MageNativeBottomline
                                                        val lblListItem: TextView =
                                                            listItemBinding.MageNativeLblListItem
                                                        val lblListItemid: TextView =
                                                            listItemBinding.MageNativeLblListItemid
                                                        lblListItem.text =
                                                            subcategory.getString("sub_category_name")
                                                        lblListItemid.text =
                                                            subcategory.getString("sub_category_id")
                                                        if (sub_cats.length() == 1) {
                                                            upperline.visibility = INVISIBLE
                                                            bottomline.visibility = INVISIBLE
                                                        } else {
                                                            if (countersub == 1) {
                                                                upperline.visibility = INVISIBLE
                                                            } else {
                                                                if (countersub == sub_cats.length()) {
                                                                    bottomline.visibility = INVISIBLE
                                                                }
                                                            }
                                                        }
                                                        subcategorysection.addView(listItemBinding.getRoot())
                                                    }
                                                    val sampleFadeAnimation =
                                                        AnimationUtils.loadAnimation(
                                                            context,
                                                            R.anim.magenative_rotate
                                                        )
                                                    sampleFadeAnimation.repeatCount = 1
                                                    sampleFadeAnimation.fillAfter = true
                                                    rotateimage.startAnimation(sampleFadeAnimation)
                                                } else {
                                                    val sampleFadeAnimation =
                                                        AnimationUtils.loadAnimation(
                                                            context,
                                                            R.anim.magenative_rotatereverse
                                                        )
                                                    sampleFadeAnimation.repeatCount = 1
                                                    sampleFadeAnimation.fillAfter = true
                                                    rotateimage.startAnimation(sampleFadeAnimation)
                                                    subcategorysection.removeAllViews()
                                                }
                                            }
                                        } else {
                //                            jumpToProductListing(maincid.text.toString().trim { it <= ' ' })
                //                            com.magentotwo.magenativeapp.base.fragment.Ced_FragmentDrawer.mDrawerLayout.closeDrawers()
                                        }
                                    } catch (e: JSONException) {
                                        e.printStackTrace()
                                    }
                                }*/
                layoutView.sideMenuCateCustomViewParent.addView(
                    categorySectionBinding.root
                )
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        //----------------specialcategory
    }

}