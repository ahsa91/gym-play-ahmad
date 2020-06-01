package controllers;

import models.Assessment;
import models.Member;
import play.Logger;
import play.mvc.Controller;
import models.Trainer;
import java.util.List;

public class TrainerDashboard extends Controller{
    public static void index() {
        Logger.info("Rendering Dashboard");
        Trainer trainer=Accounts.getLoggedInTrainer();
        List<Member> members=trainer.memberList;
        render ("trainerdashboard.html",members);

    }



    public static void deleteMember (Long id)
    {
        Logger.info("Deleting a Member");
        Trainer trainer = Accounts.getLoggedInTrainer();
        Member member = Member.findById(id);
        trainer.memberList.remove(member);
        trainer.save();
        member.delete();
        redirect ("/trainerdashboard");
    }

    public static void addMember (String name, String email, String password, String address, String gender, float height, float startingWeight, float bmi, String doctorEvaluation)
    {
        Logger.info("Adding a Member");
        Trainer trainer = Accounts.getLoggedInTrainer();
        Member member = new Member(name, email, password,address,gender,height,startingWeight,bmi,doctorEvaluation);
        trainer.memberList.add(member);
        trainer.save();
        redirect ("/trainerdashboard");
    }
}
