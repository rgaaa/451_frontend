package models

import com.google.gson.annotations.SerializedName

class UserRegistration {
    @SerializedName("full_name")
    var fullName: String? = null
    @SerializedName("date_of_birth")
    var dateOfBirth: String? = null
    @SerializedName("id_card_number")
    var idCardNumber: String? = null
    @SerializedName("phone_number")
    var phoneNumber: String? = null
    @SerializedName("email")
    var email: String? = null
    @SerializedName("city_and_country")
    var cityAndCountry: String? = null
    @SerializedName("medical_conditions")
    var medicalConditions: String? = null
    @SerializedName("username")
    var username: String? = null
    @SerializedName("password")
    var password: String? = null
}