package com.doctorjiyo.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import com.doctorjiyo.app.API.Model.PrescriptionDetails;
import com.doctorjiyo.app.R;

public class SingleMedicineAdapter extends RecyclerView.Adapter<SingleMedicineAdapter.ViewHolder> {

    private Context mcontext;
    private List<PrescriptionDetails> prescriptionDetailsList;

    public SingleMedicineAdapter(Context mcontext, List<PrescriptionDetails> prescriptionDetailsList) {
        this.mcontext = mcontext;
        this.prescriptionDetailsList = prescriptionDetailsList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(mcontext).inflate(R.layout.card_item_prescription_medicine, parent, false);

        return new SingleMedicineAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final PrescriptionDetails prescriptionDetails = prescriptionDetailsList.get(position);
        holder.DrugName.setText(prescriptionDetails.getDrugName());
        holder.Dosage.setText(prescriptionDetails.getDosage());
        holder.Qty.setText(prescriptionDetails.getQty());
        holder.FormulationType.setText(prescriptionDetails.getFormulationType());
        holder.Instruction.setText(prescriptionDetails.getInstruction());
        holder.NoOfDays.setText(prescriptionDetails.getNoOfDays());
    }

    @Override
    public int getItemCount() {
        return prescriptionDetailsList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        TextView DrugName,Dosage,Qty,FormulationType,Instruction,NoOfDays;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            DrugName=itemView.findViewById(R.id.DrugName);
            Dosage=itemView.findViewById(R.id.Dosage);
            Qty=itemView.findViewById(R.id.Qty);
            FormulationType=itemView.findViewById(R.id.FormulationType);
            Instruction=itemView.findViewById(R.id.Instruction);
            NoOfDays=itemView.findViewById(R.id.NoOfDays);





        }
    }

}
