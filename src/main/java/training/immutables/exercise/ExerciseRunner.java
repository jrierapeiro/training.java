package training.immutables.exercise;

import common.ICustomExamples;

import java.util.Arrays;
import java.util.List;

public class ExerciseRunner implements ICustomExamples {
    @Override
    public void RunExamples(){
        MembersService membersService = new MembersService();
        List<Member> members = membersService.GetStaticMembers();
        membersService.ComputeMembers(members);
    }
}
