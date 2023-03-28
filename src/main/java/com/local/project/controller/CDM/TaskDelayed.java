package com.local.project.controller.CDM;
//package com.local.complyindia.controller.CDM;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.local.complyindia.service.Auth.AuthorDataService;
//
//@RestController
//@CrossOrigin(origins = "*", maxAge = 3600)
//@RequestMapping("/api")
//public class TaskDelayed {
//	
//	@Autowired
//	AuthorDataService authorservice;
//	
//	/*-------------------Task Delayed API -start-------------------*/
//	@GetMapping("/task_completed_comp")
//	public Object TaskCompletedComp() {
//	List<List<Object>> company_details = null;
//	Long createdby = authorservice.findCurrentUser();
//	int roleid = registersRepository.getRoleId(createdby);
//	if(roleid==10 || roleid==11) {
//	company_details = registersRepository.getCDMHeadCompAllTC();
//	}
//	else if(roleid==12 || roleid==13 || roleid==14){
//	company_details = registersRepository.getCDMTeamCompAllTC(createdby.intValue());
//	}
//	return company_details;
//	}
//	//get location on company change
//	@GetMapping("/task_completed_comploc/{compid}")
//	public Object TaskCompletedCompLoc(@PathVariable(value = "compid") String compId) {
//	List<List<Object>> state_details = null;
//	state_details = registersRepository.getLocation(compId);//get location
//	return state_details;
//	}
//	//get date on company change
//	@GetMapping("/task_completed_comploc_date/{compaddid}")
//	public Object TaskCompletedCompLocDate(@PathVariable(value = "compaddid") String compAddId) {
//	String start_date = registersRepository.getLocationStartDat(Long.parseLong(compAddId));
//	return start_date;
//	}
//
//	//get activities completed
//	@GetMapping("/task_completed/{compid}/{locid}/{startdate}/{enddate}")
//	public Object TaskCompleted(
//	// @RequestBody Map<String, Object> jsondata
//	@PathVariable(value = "compid") String Compid,
//	@PathVariable(value = "locid") String Locid,
//	@PathVariable(value = "startdate") String Startdate,
//	@PathVariable(value = "enddate") String Enddate
//	) {
//	Long currentuser = authorservice.findCurrentUser();
//	int roleid = registersRepository.getRoleId(currentuser);
//	// Object companyid = jsondata.get("companyid");
//	// Object location = jsondata.get("location");
//	// Object startdate = jsondata.get("startdate");
//	// Object enddate = jsondata.get("enddate");
//	// Object userid = jsondata.get("userid");
//	Object companyid = Compid;
//	Object location = Locid;
//	Object startdate = Startdate;
//	Object enddate = Enddate;
//	Object test =null;
//	List<List<Object>> ajax_details =  new ArrayList<List<Object>>();
//	List<List<Object>> appcomp =  new ArrayList<List<Object>>();
//	List<List<Object>> r_nr_Arrayresult =  new ArrayList<List<Object>>();
//	Object reqLoc = null;
//	List<String> regStatus =  new ArrayList<String>();
//	String companiesid = null;
//	String actid = null;
//	String compaddid = null;
//	String centralact = null;
//	String stateid = null;
//	String sublocation_require  = null;
//	if(location.toString().contains("all")) {
//	if(companyid.toString().contains("viewall")) {
//	if(roleid==10 || roleid==11) {//hod
//	ajax_details = registersRepository.getHodCompletedActivityAll1(startdate.toString(),enddate.toString());
//	}
//	else if(roleid==12 || roleid==13 || roleid==14){//reportee
//	ajax_details = registersRepository.getRepCompletedActivityAll1(currentuser.intValue(),startdate.toString(),enddate.toString());
//	}
//	}
//	else {
//	ajax_details = registersRepository.getCompletedActivityIndividual1(companyid,startdate.toString(),enddate.toString());
//	}
//	}
//	else
//	{
//	if(companyid.toString().contains("viewall")) {
//	if(roleid==10 || roleid==11) {//hod
//	ajax_details = registersRepository.getHodCompletedActivityAll(location,startdate.toString(),enddate.toString());
//	}
//	else if(roleid==12 || roleid==13 || roleid==14){//reportee
//	ajax_details = registersRepository.getRepCompletedActivityAll(currentuser.intValue(),location,startdate.toString(),enddate.toString());
//	}
//	}
//	else {
//	ajax_details = registersRepository.getCompletedActivityIndividual(companyid,location,startdate.toString(),enddate.toString());
//	}
//	}
//	//r/nr calculation
//	for(List<Object> ajax_detail:ajax_details) {
//	appcomp = registersRepository.getAppComp(ajax_detail.get(0),ajax_detail.get(1),ajax_detail.get(2),ajax_detail.get(3));
//	if(appcomp!=null) {
//	for(List<Object> ac:appcomp) {
//	companiesid = ac.get(0).toString();
//	actid = ac.get(1).toString();
//	compaddid = ac.get(2).toString();
//	centralact = ac.get(3).toString();
//	stateid = ac.get(4).toString();
//	sublocation_require  = ac.get(5).toString();
//	}
//	r_nr_Arrayresult = registersRepository.getRNR(companiesid,actid,compaddid);
//	if(r_nr_Arrayresult!=null) {
//	for(List<Object> rnr:r_nr_Arrayresult) {
//	if (rnr.get(0).toString().contains("1")) {
//	regStatus.add("NR");
//	}
//	else {
//	LocalDate today = LocalDate.now();
//	// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//	// LocalDate date = LocalDate.parse(rnr.get(2).toString(), formatter);
//	// if(rnr.get(1)!=null && (rnr.get(2)==null || date.isAfter(today) || date.isEqual(today))) {
//	// regStatus.add("R");
//	// }
//	// else {
//	// if(centralact.contains("1")) {
//	// if(sublocation_require.contains("state")) {
//	// reqLoc = registersRepository.getreqLocationState(companiesid,compaddid,stateid);
//	// regStatus.add(  reqLoc!=null ? "NS" : ""  );
//	// }
//	// else if(sublocation_require.contains("city")) {
//	// reqLoc = registersRepository.getreqLocationCity(companiesid,compaddid,stateid);
//	// regStatus.add(  reqLoc!=null ? "NS" : ""  );
//	// }
//	// else if(sublocation_require.contains("address")) {
//	// reqLoc = registersRepository.getreqLocationAddress(companiesid,compaddid,stateid);
//	// regStatus.add(  reqLoc!=null ? "NS" : ""  );
//	// }
//	// else if(sublocation_require.contains("no")) {
//	// reqLoc = registersRepository.getreqLocationNo(companiesid,compaddid,stateid);
//	// regStatus.add(  reqLoc!=null ? "NS" : ""  );
//	// }
//	// }
//	// else {
//	// if(sublocation_require.contains("city")) {
//	// reqLoc = registersRepository.getreqLocationCity(companiesid,compaddid,stateid);
//	// regStatus.add(  reqLoc!=null ? "NS" : ""  );
//	// }
//	// else if(sublocation_require.contains("address")) {
//	// reqLoc = registersRepository.getreqLocationAddress(companiesid,compaddid,stateid);
//	// regStatus.add(  reqLoc!=null ? "NS" : ""  );
//	// }
//	// else if(sublocation_require.contains("no")) {
//	// reqLoc = registersRepository.getreqLocationNo(companiesid,compaddid,stateid);
//	// regStatus.add(  reqLoc!=null ? "NS" : ""  );
//	// }
//	// }
//	// }
//	}
//	}
//	}
//	else {
//	regStatus.add( "NS" );
//	}
//	}
//	else {
//	regStatus.add( "" );
//	}
//	}
//	// Map<String,List<List<Object>>> map =new HashMap();
//	// map.put("ajax_details",ajax_details);
//	// map.put("regStatus",regStatus);
//	// return map;
//	return ajax_details;
//	}
//	/*-------------------Task Completed API -end-----------*/
//}
