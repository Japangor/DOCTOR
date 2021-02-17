package com.doctorjiyo.app.API.Service;

import java.util.ArrayList;
import java.util.Map;

import com.doctorjiyo.app.API.Model.AppointmentListModel;
import com.doctorjiyo.app.API.Model.AppointmentTypeResult;
import com.doctorjiyo.app.API.Model.Doc_PatientListModel;
import com.doctorjiyo.app.API.Model.DoctorList;
import com.doctorjiyo.app.API.Model.IntroHeadingModel;
import com.doctorjiyo.app.API.Model.NotificationModel;
import com.doctorjiyo.app.API.Model.RecordCatModel;
import com.doctorjiyo.app.API.Model.HealthArticleCategoryModel;
import com.doctorjiyo.app.API.Model.HealthArticleListModel;
import com.doctorjiyo.app.API.Model.MedicalRecordModel;
import com.doctorjiyo.app.API.Model.PrescriptionDetails;
import com.doctorjiyo.app.API.Model.PrescriptionModel;
import com.doctorjiyo.app.API.Model.ResponseAttendentProfile;
import com.doctorjiyo.app.API.Model.ResponseDoctorProfile;
import com.doctorjiyo.app.API.Model.ResponsePatientProfile;
import com.doctorjiyo.app.API.Model.Result;
import com.doctorjiyo.app.API.Model.ResultHealthArticleResponse;
import com.doctorjiyo.app.API.Model.ResultLoginResponse;
import com.doctorjiyo.app.API.Model.DispensaryListResult;
import com.doctorjiyo.app.API.Model.SlotBookingResult;
import com.doctorjiyo.app.API.Model.SlotComfirmBookingResult;
import com.doctorjiyo.app.API.Model.TokenBookingResult;
import com.doctorjiyo.app.API.Model.TokenComfirmBookingResult;
import com.doctorjiyo.app.Doctor.API.Model.AppointmentList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface APIService {

    //Doctor Profile
    @GET("DoctorProfileAPI")
    Call<ResponseDoctorProfile> getDoctorProfile(
            @Query("DoctorID") String DoctorID
    );

    //AttendentProfile
    @GET("AttendantProfileAPI")
    Call<ResponseAttendentProfile> getAttedentProfile(
            @Query("AttendantId") String AttendantId
    );

    //authentication service
    @Multipart
    @POST("RegisterAPI")
    //@FormUrlEncoded
    Call<Result> SignUp(
            //@FieldMap Map<String,String>  params
            @Part("PatientName") RequestBody PatientName,
            @Part("ContactNumber") RequestBody ContactNumber,
            @Part("Email") RequestBody Email,
            @Part("Password") RequestBody Password

    );
    @GET("PatientLoginAPI")
    Call<ResultLoginResponse> PatientLogIn(
            @Query("Username") String Username,
            @Query("password") String password
    );

    @GET("DoctorLoginAPI")
    Call<ResultLoginResponse> DoctorLogIn(
            @Query("Username") String Username,
            @Query("password") String password
    );

    @GET("AttendantLoginAPI")
    Call<ResultLoginResponse> AttendantLogIn(
            @Query("Username") String Username,
            @Query("password") String password
    );


    @GET("PatientLogin/Getotp")
    Call<Result> LoginsentOTP(
            @Query("MobileNumber") String MobileNumber
    );
    @GET("PatientLogin/verificationOtp")
    Call<Result> LoginverifyOTP(
            @Query("txtOTP") String txtOTP
    );

    //Patient Profile
    @GET("PatientProfileAPI")
    Call<ResponsePatientProfile> getPatientProfile(
      @Query("PatientID") String PatientID
    );

    @Multipart
    @POST("PatientProfileAPI")
    Call<Result> editPatientProfile(
            @Part MultipartBody.Part file,
            @Part("PatientID") RequestBody PatientID,
            @Part("PatientName") RequestBody PatientName,
            @Part("ContactNumber") RequestBody ContactNumber,
            @Part("Email") RequestBody Email,
            @Part("Gender") RequestBody Gender,
            @Part("DOB") RequestBody DOB,
            @Part("BloodGroup") RequestBody BloodGroup,
            @Part("MaritalStatus") RequestBody MaritalStatus,
            @Part("Height") RequestBody Height,
            @Part("Weight") RequestBody Weight,
            @Part("EmergencyContact") RequestBody EmergencyContact,
            @Part("Location") RequestBody Location,
            @Part("SmokingHabits") RequestBody SmokingHabits,
            @Part("AlcoholConsumption") RequestBody AlcoholConsumption,
            @Part("ActivitiesLevel") RequestBody ActivitiesLevel,
            @Part("FoodPreferences") RequestBody FoodPreferences
            );


    @Multipart
    @POST("PatientAddAPI")
    Call<Result> AddPatient(
            @Part MultipartBody.Part file,
            @Part("DoctorID") RequestBody PatientID,
            @Part("PatientName") RequestBody PatientName,
            @Part("ContactNumber") RequestBody ContactNumber,
            @Part("Email") RequestBody Email,
            @Part("Gender") RequestBody Gender,
            @Part("DOB") RequestBody DOB,
            @Part("BloodGroup") RequestBody BloodGroup,
            @Part("MaritalStatus") RequestBody MaritalStatus,
            @Part("Height") RequestBody Height,
            @Part("Weight") RequestBody Weight,
            @Part("EmergencyContact") RequestBody EmergencyContact,
            @Part("Location") RequestBody Location,
            @Part("SmokingHabits") RequestBody SmokingHabits,
            @Part("AlcoholConsumption") RequestBody AlcoholConsumption,
            @Part("ActivitiesLevel") RequestBody ActivitiesLevel,
            @Part("FoodPreferences") RequestBody FoodPreferences
    );

    //Health Article
    //Health Article
    @GET("HealthArticleAPI")
    Call<ResultHealthArticleResponse> getArticle(
            @Query("HealthArticleID") String HealthArticleID
    );

    //Health Article List
    @GET("HealthArticlesListAPI")
    Call<ArrayList<HealthArticleListModel>> getArticleList(
            @Query("HealthArticleCategoriesID") String HealthArticleCategoriesID
    );
    //Health Article Categories List
    @GET("HealthArticleCategoriesAPI")
    Call<ArrayList<HealthArticleCategoryModel>> getArticleCategory();

    //Medical Record
    //Medical Record Categories List
    @GET("RecordsCategoriesAPI")
    Call<ArrayList<RecordCatModel>> getRecordCat();

    //Upload Medical Records
    @Multipart
    @POST("RecordsAPI")
    Call<Result> uploadRecord(
            @Part("RecordName") RequestBody RecordName,
            @Part("Notes") RequestBody Notes,
            @Part("CreatedAt") RequestBody CreatedAt,
            @Part MultipartBody.Part file,
            @Part("PatientID") RequestBody PatientID,
            @Part("PrescriptionID") RequestBody PrescriptionID,
            @Part("RecordCatName") RequestBody RecordCatID
    );

    //Medical Records List
    @GET("RecordsAPI")
    Call<ArrayList<MedicalRecordModel>> getRecordList(
            @Query("PatientID") String PatientID
    );

    @GET("RecordsAPI")
    Call<ArrayList<MedicalRecordModel>> getPrescriptionRecordList(
            @Query("PrescriptionID") String PrescriptionID
    );

    //Edit Medical Record
    @Multipart
    @POST("RecordUpdateAPI")
    Call <Result> editRecord(
            @Part MultipartBody.Part file,
            @Part("PatientID") RequestBody PatientID,
            @Part("RecordID") RequestBody RecordID,
            @Part("Notes") RequestBody Notes,
            @Part("CreatedAt") RequestBody CreatedAt,
            @Part("RecordName") RequestBody RecordName
    );

    //Prescription
    //Prescription List
    @GET("PrescriptionsAPI")
    Call<ArrayList<PrescriptionModel>> getPrecriptionsList(
            @Query("PatientID") String PatientID
    );
    //Medicine List
    @GET("PrescriptionDetailsAPI")
    Call<ArrayList<PrescriptionDetails>> getPrescriptionDetails(
            @Query("PrescriptionID") String PrescriptionID
    );


    @GET("IntroSlidesAPI")
    Call<IntroHeadingModel> getIntroSlide();

    //Medicine List
    @GET("NotificationAPI")
    Call<ArrayList<NotificationModel>> getNotificationList(
            @Query("PatientID") String PatientID
    );


    @GET("PatientsListAPI")
    Call<ArrayList<Doc_PatientListModel>> getPatientList(
            @Query("DoctorID") String DoctorID
    );

    @GET("PatientsListAPI")
    Call<ArrayList<Doc_PatientListModel>> getSearchPatientList(
            @Query("DoctorID") String DoctorID,
            @Query("SearchTerm") String SearchTerm
            );

    @GET("DoctorsListAPI")
    Call<ArrayList<DoctorList>> getDoctorList();

    //Appointment API
    @GET("AppointmentTypeAPI")
    Call<AppointmentTypeResult> getAppointmentType();

    @GET("TokenSelectAPI")
    Call<ArrayList<DispensaryListResult>> getToken(
            @Query("DoctorID") String DoctorID,
            @Query("BookingDate") String BookingDate
            );

    @GET("TokenBookingAPI")
    Call<TokenBookingResult> getTokenBooking(
            @Query("DispensaryID") String DispensaryID,
            @Query("DoctorID") String DoctorID,
            @Query("PatientID") String BookingDate
    );



    @POST("TokenBookingAPI")
    @FormUrlEncoded
    Call<Result> submitTokenAppointment(
            @FieldMap Map<String,String> params

    );

    @GET("TokenConfirmAPI")
    Call<TokenComfirmBookingResult> getTokenConfirm(
            @Query("DispensaryID") String DispensaryID,
            @Query("DoctorID") String DoctorID,
            @Query("PatientID") String PatientID,
            @Query("BookingDate") String BookingDate
    );


    @GET("SlotSelectAPI")
    Call<ArrayList<DispensaryListResult>> getSlot(
            @Query("DoctorID") String DoctorID,
            @Query("BookingDate") String BookingDate
    );

    @GET("SlotBookingAPI")
    Call<SlotBookingResult> getSlotBooking(
            @Query("DispensaryID") String DispensaryID,
            @Query("DoctorID") String DoctorID,
            @Query("PatientID") String BookingDate
    );

    @POST("SlotBookingAPI")
    @FormUrlEncoded
    Call<Result> submitSlotAppointment(
            @FieldMap Map<String,String> params

    );
    @GET("SlotConfirmAPI")
    Call<SlotComfirmBookingResult> getSlotConfirm(
            @Query("DispensaryID") String DispensaryID,
            @Query("DoctorID") String DoctorID,
            @Query("PatientID") String PatientID,
            @Query("BookingDate") String BookingDate
    );

    @GET("AppointmentListAPI")
    Call<ArrayList<AppointmentListModel>> getAppointmentList(
            @Query("PatientID") String PatientID
    );


}
