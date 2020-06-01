package models;

import play.db.jpa.Model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Member extends Model {
    public String name;
    public String email;
    public String password;
    public String address;
    public String gender;
    public float height;
    public float startingWeight;
    public float bmi;
    public String doctorEvaluation;
    @OneToMany(cascade = CascadeType.ALL)

    public List<Assessment> assessments = new ArrayList<Assessment>();


    public Member(String name, String email, String password, String address, String gender, float height, float startingWeight, float bmi, String doctorEvaluation) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
        this.gender = gender;
        this.height = height;
        this.startingWeight = startingWeight;
        this.bmi = bmi;
        this.doctorEvaluation = doctorEvaluation;

    }

    public float getBmi() {
        float BMI = 0;
        if (assessments.size() > 0) {
            Assessment assessment = assessments.get(assessments.size() - 1);
            BMI = assessment.weight / (height * height);
        }

        return BMI;
    }

    public String getDoctorEvaluation() {
        if (getBmi() > 30) {
            return "You are obese. Please work harder";
        } else if ((getBmi() > 24.99) && (getBmi() < 30)) {
            return "You're overweight! Please work harder";
        }else if((getBmi()>18.5)&&(getBmi()<24.99)){
            return "You're at a normal weight! Great job!";
        }else
            return "You're under weight! Lets take a look at your diet";
    }


    public static Member findByEmail(String email)
    {
        return find("email", email).first();
    }

    public boolean checkPassword(String password)
    {
        return  this.password.equals(password);
    }

}


