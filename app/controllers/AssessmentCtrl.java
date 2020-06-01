package controllers;

import java.util.List;

import models.Member;
import models.Assessment;
import play.Logger;
import play.mvc.Controller;

public class AssessmentCtrl extends Controller {
    public static void index(Long id)
    {
        Member member= Member.findById(id);
        Logger.info("Member id="+ id);
        render("member.html",member);
    }

    public static void deleteassessment(Long id, Long assessid){
        Member member= Member.findById(id);
        Assessment assess=Assessment.findById(assessid);
        Logger.info("Removing"+assess.weight+
                assess.chest+assess.thigh+assess.waist+
                assess.upperArm+assess.hips);
        member.assessments.remove(assess);
        member.save();
        assess.delete();
        render("member.html",member);

    }

    public static void addassessment(Long id,float weight,float chest,float thigh,float upperArm,float waist,float hips){

        Assessment assess=new Assessment(weight, chest, thigh, upperArm, waist, hips);
        Member member=Member.findById(id);
        member.assessments.add(assess);
        member.save();
        redirect("/members/"+id);
    }
}
