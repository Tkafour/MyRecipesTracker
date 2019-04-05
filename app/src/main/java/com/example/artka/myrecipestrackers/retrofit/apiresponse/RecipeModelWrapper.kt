package com.example.artka.myrecipestrackers.retrofit.apiresponse

import androidx.annotation.NonNull
import androidx.room.*
import java.util.Collections.emptyList

data class RecipeModelWrapper(
        val count: Int,
        val from: Int,
        val hits: List<Hit>,
        val more: Boolean,
        val params: Params,
        val q: String,
        val to: Int,
        val totalDaily: String,
        val totalNutrients: String
)

@Entity(tableName = "recipes")
data class RecipeModel(
        @NonNull
        @PrimaryKey(autoGenerate = true)
        var id: Int,
        var calories: Double,
        /*@Ignore
        var cautions: List<Any>,*/
        var dietLabels: List<String>,
        var digest: List<Digest>,
        var healthLabels: List<String>,
        var image: String,
        var ingredientLines: List<String>,
        var ingredients: List<Ingredient>,
        var label: String,
        var shareAs: String,
        var source: String,
//        var totalDaily: TotalDaily,
//        var totalNutrients: TotalNutrients,
        var totalTime: Double,
        var totalWeight: Double,
        var uri: String,
        var url: String,
        var yield: Double
) {
    constructor() : this(0, 0.0, emptyList(), emptyList(), emptyList(),
            "", emptyList(), emptyList(), "", "", "", 0.0, 0.0, "", "",0.0)
}

data class NutrientValue(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class Digest(
        val daily: Double,
        val hasRDI: Boolean,
        val label: String,
        val schemaOrgTag: Any,
        val sub: List<Sub>,
        val tag: String,
        val total: Double,
        val unit: String
)

data class Hit(
        val bookmarked: Boolean,
        val bought: Boolean,
        val recipe: RecipeModel
)

data class Ingredient(
        val text: String,
        val weight: Double
)

data class Params(
        val app_id: List<String>,
        val app_key: List<String>,
        val calories: List<String>,
        val from: List<String>,
        val health: List<String>,
        val q: List<String>,
        val sane: List<Any>,
        val to: List<String>
)

data class Sub(
        val daily: Double,
        val hasRDI: Boolean,
        val label: String,
        val schemaOrgTag: Any,
        val tag: String,
        val total: Double,
        val unit: String
)

data class TotalDaily(
  val CA: NutrientValue = NutrientValue("", 0.0, ""),
  val CHOCDF: NutrientValue = NutrientValue("", 0.0, ""),
  val CHOLE: NutrientValue = NutrientValue("", 0.0, ""),
  val ENERC_KCAL: NutrientValue = NutrientValue("", 0.0, ""),
  val FASAT: NutrientValue = NutrientValue("", 0.0, ""),
  val FAT: NutrientValue = NutrientValue("", 0.0, ""),
  val FE: NutrientValue = NutrientValue("", 0.0, ""),
  val FIBTG: NutrientValue = NutrientValue("", 0.0, ""),
  val FOLDFE: NutrientValue = NutrientValue("", 0.0, ""),
  val K: NutrientValue = NutrientValue("", 0.0, ""),
  val MG: NutrientValue = NutrientValue("", 0.0, ""),
  val NA: NutrientValue = NutrientValue("", 0.0, ""),
  val NIA: NutrientValue = NutrientValue("", 0.0, ""),
  val P: NutrientValue = NutrientValue("", 0.0, ""),
  val PROCNT: NutrientValue = NutrientValue("", 0.0, ""),
  val RIBF: NutrientValue = NutrientValue("", 0.0, ""),
  val THIA: NutrientValue = NutrientValue("", 0.0, ""),
  val TOCPHA: NutrientValue = NutrientValue("", 0.0, ""),
  val VITA_RAE: NutrientValue = NutrientValue("", 0.0, ""),
  val VITB12: NutrientValue = NutrientValue("", 0.0, ""),
  val VITB6A: NutrientValue = NutrientValue("", 0.0, ""),
  val VITC: NutrientValue = NutrientValue("", 0.0, ""),
  val VITD: NutrientValue = NutrientValue("", 0.0, ""),
  val VITK1: NutrientValue = NutrientValue("", 0.0, ""),
  val ZN: NutrientValue = NutrientValue("", 0.0, "")
)

data class TotalNutrients(
        val CA: NutrientValue = NutrientValue("", 0.0, ""),
        val CHOCDF: NutrientValue = NutrientValue("", 0.0, ""),
        val CHOLE: NutrientValue = NutrientValue("", 0.0, ""),
        val ENERC_KCAL: NutrientValue = NutrientValue("", 0.0, ""),
        val FAMS: NutrientValue = NutrientValue("", 0.0, ""),
        val FAPU: NutrientValue = NutrientValue("", 0.0, ""),
        val FASAT: NutrientValue = NutrientValue("", 0.0, ""),
        val FAT: NutrientValue = NutrientValue("", 0.0, ""),
        val FATRN: NutrientValue = NutrientValue("", 0.0, ""),
        val FE: NutrientValue = NutrientValue("", 0.0, ""),
        val FIBTG: NutrientValue = NutrientValue("", 0.0, ""),
        val FOLDFE: NutrientValue = NutrientValue("", 0.0, ""),
        val FOLFD: NutrientValue = NutrientValue("", 0.0, ""),
        val K: NutrientValue = NutrientValue("", 0.0, ""),
        val MG: NutrientValue = NutrientValue("", 0.0, ""),
        val NA: NutrientValue = NutrientValue("", 0.0, ""),
        val NIA: NutrientValue = NutrientValue("", 0.0, ""),
        val P: NutrientValue = NutrientValue("", 0.0, ""),
        val PROCNT: NutrientValue = NutrientValue("", 0.0, ""),
        val RIBF: NutrientValue = NutrientValue("", 0.0, ""),
        val SUGAR: NutrientValue = NutrientValue("", 0.0, ""),
        val THIA: NutrientValue = NutrientValue("", 0.0, ""),
        val TOCPHA: NutrientValue = NutrientValue("", 0.0, ""),
        val VITA_RAE: NutrientValue = NutrientValue("", 0.0, ""),
        val VITB12: NutrientValue = NutrientValue("", 0.0, ""),
        val VITB6A: NutrientValue = NutrientValue("", 0.0, ""),
        val VITC: NutrientValue = NutrientValue("", 0.0, ""),
        val VITD: NutrientValue = NutrientValue("", 0.0, ""),
        val VITK1: NutrientValue = NutrientValue("", 0.0, ""),
        val ZN: NutrientValue = NutrientValue("", 0.0, "")
)
