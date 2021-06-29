package ua.training.controller.command;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.training.model.entity.Subject;
import ua.training.model.entity.Test;
import ua.training.model.entity.User;
import ua.training.model.service.SubjectService;
import ua.training.model.service.TestService;
import ua.training.model.service.impl.SubjectServiceImpl;
import ua.training.model.service.impl.TestServiceImpl;

import static ua.training.constants.Constants.APP_NAME;
import static ua.training.constants.Constants.INITIAL_PAGE_NUMBER;
import static ua.training.constants.Constants.RECORDS_PER_PAGE;
import static ua.training.constants.Constants.DEFAULT_LANGUAGE;
import static ua.training.constants.SqlConstants.SELECT_N_TESTS_BY_SUBJECT;

public class TestListingCommand implements Command{
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
		TestService testService = new TestServiceImpl();
		SubjectService subjectService = new SubjectServiceImpl();
		List<Subject> subjects = subjectService.getAllSubjects();
		List<Test> tests;
		
        int page = request.getParameter("page") != null?
        		Integer.parseInt(request.getParameter("page")):INITIAL_PAGE_NUMBER;
        
        int noOfRecords = testService.getTestsCount();
        
        String lastSort = null;
        
        if(request.getParameter("selectedSubject")==null&&request.getParameter("orderBy")==null) {
        	lastSort = (String) request.getSession().getAttribute("lastSort");
        }
        
		if(request.getParameter("selectedSubject")!=null&&!"0".equals(request.getParameter("selectedSubject"))
				||"bySubject".equals(lastSort)) {
			
			int selectedSubject = request.getParameter("selectedSubject")!=null?
					Integer.parseInt(request.getParameter("selectedSubject")):
						(int) request.getSession().getAttribute("lastSelectedSubject");
			
			request.getSession().setAttribute("selectedSubject", selectedSubject);
			request.getSession().setAttribute("lastSort", "bySubject");
			request.getSession().setAttribute("lastSelectedSubject", selectedSubject);
			
			noOfRecords = testService.getTestsCountBySubjectId(selectedSubject);
			
			tests = testService.findTestsByPageAndSubjectId(selectedSubject,(page-1)*RECORDS_PER_PAGE, RECORDS_PER_PAGE);
		
		}
		else if(request.getParameter("orderBy")!=null || "byColumnName".equals(lastSort)){
			String orderBy = request.getParameter("orderBy")!=null?
					request.getParameter("orderBy"):
						(String) request.getSession().getAttribute("lastOrderBy");
			
			request.getSession().setAttribute("orderBy", orderBy);
			request.getSession().setAttribute("lastOrderBy", orderBy);
			
			Boolean descSort = orderBy.contains("desc")?true:false;

			orderBy = descSort?orderBy.replaceAll("_desc", ""):orderBy;
			
			if(!orderBy.contains("requests")) {
				orderBy+=request.getParameter("language")!=null?request.getParameter("language"):DEFAULT_LANGUAGE;
			}
			
			request.getSession().setAttribute("lastSort", "byColumnName");

			System.out.println("OrderBy "+orderBy);
			
			tests = testService.findTestsByPageSorted((page-1)*RECORDS_PER_PAGE, RECORDS_PER_PAGE, descSort,  orderBy);
		}
		else {
			tests = testService.findTestsByPageSorted((page-1)*RECORDS_PER_PAGE, RECORDS_PER_PAGE, false, SELECT_N_TESTS_BY_SUBJECT);	
			request.getSession().setAttribute("selectedSubject", request.getParameter("selectedSubject"));
		}
			
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);

		request.getSession().setAttribute("tests", tests);
		request.getSession().setAttribute("noOfPages", noOfPages);
        request.getSession().setAttribute("currentPage", page);
        request.getSession().setAttribute("subjects", subjects);
        
        User user = (User) request.getSession().getAttribute("User");
        
        if("ADMIN".equals(user.getRole().toString())) {
        	return "redirect:"+APP_NAME+"/account/admin/adminbasis.jsp";
        }
        return "redirect:"+APP_NAME+"/account/student/studentbasis.jsp";
    }	
}
