public class EmployeeDetails {
    String email, firstName, lastName, phoneNo, location, empId, vmodel, regNo;

    public EmployeeDetails(String email, String name, String phoneNo, String location,
                           String empId, String vmodel, String regNo){

        this.email = email;
        this.phoneNo = phoneNo;
        this.location = location;
        this.empId = empId;
        this.vmodel = vmodel;
        this.regNo = regNo;

        String[] arrOfStr = name.split(" ");
        this.firstName = arrOfStr[0];
        this.lastName = arrOfStr[1];

    }
}
