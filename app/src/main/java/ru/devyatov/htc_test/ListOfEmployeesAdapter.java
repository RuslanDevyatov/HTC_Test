package ru.devyatov.htc_test;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListOfEmployeesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public final static int WRONG_ITEM_TYPE = -1;
    public final static int EMPLOYEE_ITEM_TYPE = 0;
    public final static int COMPANY_ITEM_TYPE = 1;

    public final static String BLANK_FIELD = "⸻";

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

            name = itemView.findViewById(R.id.rv_employee_item_name_value);
            phoneNumber = itemView.findViewById(R.id.rv_employee_item_phone_number_value);
            skills = itemView.findViewById(R.id.rv_employee_item_skills_value);
        }

        public void onBind(Employee employee) {

            String mName = BLANK_FIELD;
            if (employee.getName() != null) {
                mName = employee.getName();
            }

            String mPhoneNumber = BLANK_FIELD;
            if (employee.getPhoneNumber() > 0) {
                mPhoneNumber = Integer.toString(employee.getPhoneNumber());
            }

            String mSkills = BLANK_FIELD;
            if (employee.getSkills() != null) {
                mSkills = TextUtils.join(", ", employee.getSkills());
            }

            name.setText(mName);
            phoneNumber.setText(mPhoneNumber);
            skills.setText(mSkills);
        }
    }

    public static class CompanyViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private TextView age;
        private TextView competences;

        public CompanyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.rv_company_item_name_value);
            age = itemView.findViewById(R.id.rv_company_age_value);
            competences = itemView.findViewById(R.id.rv_company_competences_value);
        }

        public void onBind(Company company) {

            String mName = BLANK_FIELD;
            if (company.getName() != null) {
                mName = company.getName();
            }

            String mAge = BLANK_FIELD;
            if (company.getAge() > 0) {
                mAge = Integer.toString(company.getAge());
            }

            String mCompetences = BLANK_FIELD;
            if (company.getCompetences() != null) {
                mCompetences = TextUtils.join(", ", company.getCompetences());
            }

            name.setText(mName);
            age.setText(mAge);
            competences.setText(mCompetences);
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
            default:
                throw new RuntimeException("Wrong item type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Object obj = list.get(position);

        if (obj instanceof Company) {
            ((CompanyViewHolder)holder).onBind((Company) obj);
        } else if (obj instanceof Employee) {
            ((EmployeeViewHolder)holder).onBind((Employee) obj);
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

        if (obj instanceof Company) {
            return COMPANY_ITEM_TYPE;
        } else if (obj instanceof Employee) {
            return EMPLOYEE_ITEM_TYPE;
        } else {
            return WRONG_ITEM_TYPE;
        }
    }
}
