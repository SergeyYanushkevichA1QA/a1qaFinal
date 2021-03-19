package by.a1qa.database;

public class Query {

    public static String addTest(String projectName, String testName, String testMethod, String testEnv) {
        return "insert into test (test.name,test.method,test.env)" +
                "values ("+ testName +","+ testMethod +","+testEnv+") JOIN project ON project.id = test.project_id where project.name='"+projectName +"' ;";
    }
}
