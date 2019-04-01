package com.example.artka.myrecipestrackers.retrofit.apiresponse

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.*
import com.example.artka.myrecipestrackers.database.RoomObjectConverters
import com.example.artka.myrecipestrackers.database.RoomStringTypeConverters

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
/*      @Ignore
        var totalDaily: TotalDaily,
        @Ignore
        var totalNutrients: TotalNutrients,*/
        var totalTime: Double,
        var totalWeight: Double,
        var uri: String,
        var url: String,
        var yield: Double
) {
    constructor() : this(0, 0.0, emptyList(), emptyList(), emptyList(),
            "", emptyList(), emptyList(), "", "", "", 0.0, 0.0, "", "", 0.0)
}

data class CA(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class CHOCDF(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class CHOLE(
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

data class ENERCKCAL(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class FAMS(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class FAPU(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class FASAT(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class FAT(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class FATRN(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class FE(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class FIBTG(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class FOLDFE(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class FOLFD(
        val label: String,
        val quantity: Double,
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

data class K(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class MG(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class NA(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class NIA(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class P(
        val label: String,
        val quantity: Double,
        val unit: String
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

data class PROCNT(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class RIBF(
        val label: String,
        val quantity: Double,
        val unit: String
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

data class SUGAR(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class THIA(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class TOCPHA(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class TotalDaily(
        val CA: CA,
        val CHOCDF: CHOCDF,
        val CHOLE: CHOLE,
        val ENERC_KCAL: ENERCKCAL,
        val FASAT: FASAT,
        val FAT: FAT,
        val FE: FE,
        val FIBTG: FIBTG,
        val FOLDFE: FOLDFE,
        val K: K,
        val MG: MG,
        val NA: NA,
        val NIA: NIA,
        val P: P,
        val PROCNT: PROCNT,
        val RIBF: RIBF,
        val THIA: THIA,
        val TOCPHA: TOCPHA,
        val VITA_RAE: VITARAE,
        val VITB12: VITB12,
        val VITB6A: VITB6A,
        val VITC: VITC,
        val VITD: VITD,
        val VITK1: VITK1,
        val ZN: ZN
)

data class TotalNutrients(
        val CA: CA,
        val CHOCDF: CHOCDF,
        val CHOLE: CHOLE,
        val ENERC_KCAL: ENERCKCAL,
        val FAMS: FAMS,
        val FAPU: FAPU,
        val FASAT: FASAT,
        val FAT: FAT,
        val FATRN: FATRN,
        val FE: FE,
        val FIBTG: FIBTG,
        val FOLDFE: FOLDFE,
        val FOLFD: FOLFD,
        val K: K,
        val MG: MG,
        val NA: NA,
        val NIA: NIA,
        val P: P,
        val PROCNT: PROCNT,
        val RIBF: RIBF,
        val SUGAR: SUGAR,
        val THIA: THIA,
        val TOCPHA: TOCPHA,
        val VITA_RAE: VITARAE,
        val VITB12: VITB12,
        val VITB6A: VITB6A,
        val VITC: VITC,
        val VITD: VITD,
        val VITK1: VITK1,
        val ZN: ZN
)

data class VITARAE(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class VITB6A(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class VITB12(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class VITC(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class VITD(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class VITK1(
        val label: String,
        val quantity: Double,
        val unit: String
)

data class ZN(
        val label: String,
        val quantity: Double,
        val unit: String
)