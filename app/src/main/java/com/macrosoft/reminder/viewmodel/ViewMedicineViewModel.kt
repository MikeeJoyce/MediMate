package com.macrosoft.reminder.viewmodel

import android.util.Log
import androidx.databinding.Bindable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hadilq.liveevent.LiveEvent
import com.macrosoft.reminder.model.MedicineData
import com.macrosoft.reminder.model.MedicineDetails
import com.macrosoft.reminder.model.MedicineDetailsList
import com.macrosoft.reminder.repository.FakeRepository
import com.macrosoft.reminder.repository.MedicineRepository

class ViewMedicineViewModel(private val med_repo: MedicineRepository) : ObservableViewModel() {

    companion object {
        private val TAG = ViewMedicineViewModel::class.java.simpleName
    }

    @Bindable
    val medicineNameInputContent = MutableLiveData<String>()

    @Bindable
    val dosageInputContent = MutableLiveData<String>()

    @Bindable
    val requirementsInputContent = MutableLiveData<String>()

    private val medicineDetails = LiveEvent<MedicineDetailsList>()
    val state: LiveData<MedicineDetailsList> = medicineDetails

    private val medicineDetailsItem = LiveEvent<MedicineDetails>()
    val itemState: LiveData<MedicineDetails> = medicineDetailsItem

    private val fakeMedicineDetails: LiveData<MedicineDetailsList>
        get() = FakeRepository.fakeMedicineDetails

    private val showAddReminderFragment = LiveEvent<Boolean>()
    val addReminderFragmentState: LiveData<Boolean> = showAddReminderFragment


    fun getUserMedicineData(userID : Int): LiveData<Array<MedicineData>> {
        return med_repo.getMedicineByUserID(userID)
    }

    fun setMedicineDetailsDatabaseID(id: Int) {

        // TODO: Get the Reminder from DB using the @id parameter and set the Reminder to medicineDetails.value
        medicineDetails.value = fakeMedicineDetails.value
    }

    // The State represents the currently selected medicine to be edited, this method sets the state
    fun setItemState(medicineDetails: MedicineDetails) {
        medicineDetailsItem.value = medicineDetails
    }

    fun onScheduleClick() {

        // TODO: Open date picker on click to select schedule
        Log.i(TAG, "onScheduleClick()")

        Log.i(TAG, medicineNameInputContent.value)
        Log.i(TAG, dosageInputContent.value)
        Log.i(TAG, requirementsInputContent.value)
    }

    fun onAlarmClick() {

        // TODO: Open the alarm selection on clicking
        Log.i(TAG, "onAlarmClick()")
    }

    fun onDeleteMedClick() {

        // TODO: Update the DB entity "MedicineDetailsList" by deleting a medicine "MedicineDetails" member variable from the current DB entity
        Log.i(TAG, "onDeleteMedClick()")
    }

    fun onSaveMedClick() {

        // TODO: Update itemState here and update the DB entity "MedicineDetailsList" to save the new values
        Log.i(TAG, "onSaveMedClick()")
    }

    // Set the initial values of the Bindable EditText fields onStart() from the Observer (Fragment) according to the State
    fun setEditInputInitialValues(itemState: MedicineDetails) {

        Log.i(TAG, "setEditInputInitialValues()")

        medicineNameInputContent.value = itemState.name
        dosageInputContent.value = itemState.dosage
        requirementsInputContent.value = itemState.requirements
    }

    fun onAddMedicineClick() {
        Log.i(TAG, "onAddMedicineClick()")
        showAddReminderFragment.value = true
    }
}