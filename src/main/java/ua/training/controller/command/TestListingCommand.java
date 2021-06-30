package ua.training.controller.command;

import java.util.List;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.training.model.entity.Subject;
import ua.training.model.entity.Test;
import ua.training.model.entity.User;
import ua.training.model.service.ServiceFactory;
import ua.training.model.service.SubjectService;
import ua.training.model.service.TestService;

import static ua.training.constants.Constants.APP_NAME;
import static ua.training.constants.Constants.INITIAL_PAGE_NUMBER;
import static ua.training.constants.Constants.RECORDS_PER_PAGE;
import static ua.training.constants.Constants.DEFAULT_LANGUAGE;
import static ua.training.constants.SqlConstants.SELECT_N_TESTS_BY_SUBJECT;

public class TestListingCommand implements Command{
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
		TestService testService = ServiceFactory.getInstance().createTestService();
		SubjectService subjectService = ServiceFactory.getInstance().createSubjectService();
		
		List<Subject> subjects = subjectService.getAllSubjects();
		List<Test> tests;
		
        int page = request.getParameter("page") != null?
        		Integer.parseInt(request.getParameter("page")):INITIAL_PAGE_NUMBER;
        
        int noOfRecords = testService.getTestsCount();
        
        String lastSort = null;
        
        String selectedSubject = request.getParameter("selectedSubject");
        String orderBy = request.getParameter("orderBy");
        String language = request.getParameter("language");
        
        if(selectedSubject==null&&orderBy==null) {
        	lastSort = (String) request.getSession().getAttribute("lastSort");
        }
        
		if(selectedSubject!=null&&!"0".equals(selectedSubject)
				||"bySubject".equals(lastSort)) {
			
			int subjectId = selectedSubject!=null?
					Integer.parseInt(selectedSubject):
						(int) request.getSession().getAttribute("lastSelectedSubject");
			
			request.getSession().setAttribute("selectedSubject", selectedSubject);
			request.getSession().setAttribute("lastSort", "bySubject");
			request.getSession().setAttribute("lastSelectedSubject", selectedSubject);
			
			noOfRecords = testService.getTestsCountBySubjectId(subjectId);
						
			tests = testService.findTestsByPageAndSubjectId(subjectId,(page-1)*RECORDS_PER_PAGE, RECORDS_PER_PAGE);
		
		}
		else if(orderBy!=null || "byColumnName".equals(lastSort)){
			orderBy = orderBy!=null?orderBy:
						(String) request.getSession().getAttribute("lastOrderBy");
			
			request.getSession().setAttribute("orderBy", orderBy);
			request.getSession().setAttribute("lastOrderBy", orderBy);
			
			Boolean descSort = orderBy.contains("desc")?true:false;

			orderBy = orderBy.replaceAll("_desc", "");
					
			if(!orderBy.contains("requests")) {
				orderBy+="_";
				orderBy+= "_" + (language!=null?language:DEFAULT_LANGUAGE);
			}
			
			request.getSession().setAttribute("lastSort", "byColumnName");
			
			tests = testService.findTestsByPageSorted((page-1)*RECORDS_PER_PAGE, RECORDS_PER_PAGE, descSort,  orderBy);
		}
		else {
			tests = testService.findTestsByPageSorted((page-1)*RECORDS_PER_PAGE, RECORDS_PER_PAGE, false, SELECT_N_TESTS_BY_SUBJECT);	
			request.getSession().setAttribute("selectedSubject", selectedSubject);
			request.getSession().setAttribute("lastSort", "");
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
