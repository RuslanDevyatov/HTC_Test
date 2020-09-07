package ru.devyatov.htc_test;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListOfEmployeesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder > {

    public final static int EMPLOYEE_ITEM_TYPE = 0;
    public final static int COMPANY_ITEM_TYPE = 1;

    private List<Object> list;

    public ListOfEmployeesAdapter(List<Object> list) {
        this.list = list;
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView phoneNumber;
        private TextView skills;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.rv_employee_item_name_value);
            phoneNumber = (TextView) itemView.findViewById(R.id.rv_employee_item_phone_number_value);
            skills = (TextView) itemView.findViewById(R.id.rv_employee_item_skills_value);
        }
    }

    public static class CompanyViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView age;
        private TextView competences;

        public CompanyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.rv_company_item_name_value);
            age = (TextView) itemView.findViewById(R.id.rv_company_age_value);
            competences = (TextView) itemView.findViewById(R.id.rv_company_competences_value);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        
        View view;
        switch (viewType) {
            case EMPLOYEE_ITEM_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_employee_item, parent,false);
                return new EmployeeViewHolder(view);
            case COMPANY_ITEM_TYPE:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_company_item, parent,false);
                return new CompanyViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Object obj = list.get(position);
        switch (obj.getClass().getSimpleName()) {
            case "Employee":
                Employee employee = (Employee)obj;

                String name = "⸻";
                if (employee.getName() != null) {
                    name = employee.getName();
                }

                String phoneNumber = "⸻";
                if (employee.getPhoneNumber() > 0) {
                    phoneNumber = Integer.toString(employee.getPhoneNumber());
                }

                String skills = "⸻";
                if (employee.getSkills() != null) {
                    skills = TextUtils.join(", ", employee.getSkills());
                }


                ((EmployeeViewHolder) holder).name.setText(name);
                ((EmployeeViewHolder) holder).phoneNumber.setText(phoneNumber);
                ((EmployeeViewHolder) holder).skills.setText(skills);
                break;
            case "Company":
                Company company = (Company) obj;
                ((CompanyViewHolder) holder).name.setText(company.getName());
                ((CompanyViewHolder) holder).age.setText(Integer.toString(company.getAge()));
                ((CompanyViewHolder) holder).competences.setText(
                        TextUtils.join(", ", company.getCompetences()));
                break;
        }
    }

    @Override
    public int getItemCount() {
        if (list == null) {
            return 0;
        }
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {

        Object obj = list.get(position);
        switch (obj.getClass().getSimpleName()) {
            case "Employee":
                return EMPLOYEE_ITEM_TYPE;
            case "Company":
                return COMPANY_ITEM_TYPE;
        }
        return 0;
    }
}
