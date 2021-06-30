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

import static ua.training.constants.Constants.*;
import static ua.training.constants.SqlConstants.*;

public class TestListingCommand implements Command{
	@Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
		TestService testService = ServiceFactory.getInstance().createTestService();
		SubjectService subjectService = ServiceFactory.getInstance().createSubjectService();
		
		List<Subject> subjects = subjectService.getAllSubjects();
		List<Test> tests;
		
        int page = request.getParameter(PAGE) != null?
        		Integer.parseInt(request.getParameter(PAGE)):INITIAL_PAGE_NUMBER;
        
        int noOfRecords = testService.getTestsCount();
        
        String lastSort = null;
        
        String selectedSubject = request.getParameter(SELECTED_SUBJECT);
        String orderBy = request.getParameter(ORDER_BY);
        String language = request.getParameter(LANGUAGE);
        
        if(selectedSubject==null&&orderBy==null) {
        	lastSort = (String) request.getSession().getAttribute(LAST_SORT);
        }
        
		if(selectedSubject!=null&&!FIRST_ELEMENT.equals(selectedSubject)
				||SORT_BY_SUBJECT.equals(lastSort)) {
			
			int subjectId = selectedSubject!=null?
					Integer.parseInt(selectedSubject):
						(int) request.getSession().getAttribute(LAST_SELECTED_SORT);
			
			request.getSession().setAttribute(SELECTED_SUBJECT, selectedSubject);
			request.getSession().setAttribute(LAST_SORT, SORT_BY_SUBJECT);
			request.getSession().setAttribute(LAST_SELECTED_SUBJECT, selectedSubject);
			
			noOfRecords = testService.getTestsCountBySubjectId(subjectId);
						
			tests = testService.findTestsByPageAndSubjectId(subjectId,(page-1)*RECORDS_PER_PAGE, RECORDS_PER_PAGE);
		
		}
		else if(orderBy!=null || SORT_BY_COLUMN.equals(lastSort)){
			orderBy = orderBy!=null?orderBy:
						(String) request.getSession().getAttribute(LAST_ORDER_BY);
			
			request.getSession().setAttribute(ORDER_BY, orderBy);
			request.getSession().setAttribute(LAST_ORDER_BY, orderBy);
			
			Boolean descSort = orderBy.contains("desc")?true:false;

			orderBy = orderBy.replaceAll("_desc", "");
					
			if(!orderBy.contains("requests")) {
				orderBy+="_";
				orderBy+= "_" + (language!=null?language:DEFAULT_LANGUAGE);
			}
			
			request.getSession().setAttribute(LAST_SORT, SORT_BY_COLUMN);
			
			tests = testService.findTestsByPageSorted((page-1)*RECORDS_PER_PAGE, RECORDS_PER_PAGE, descSort,  orderBy);
		}
		else {
			tests = testService.findTestsByPageSorted((page-1)*RECORDS_PER_PAGE, RECORDS_PER_PAGE, false, SELECT_N_TESTS_BY_SUBJECT);	
			request.getSession().setAttribute(SELECTED_SUBJECT, selectedSubject);
			request.getSession().setAttribute(LAST_SORT, "");
		}
			
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / RECORDS_PER_PAGE);

		request.getSession().setAttribute("tests", tests);
		request.getSession().setAttribute("noOfPages", noOfPages);
        request.getSession().setAttribute("currentPage", page);
        request.getSession().setAttribute("subjects", subjects);
        
        User user = (User) request.getSession().getAttribute(USER);
        
        if(ADMIN.equalsIgnoreCase(user.getRole().toString())) {
        	return REDIRECT+APP_NAME+ADMIN_PAGE_PATH;
        }
        return REDIRECT+APP_NAME+STUDENT_PAGE_PATH;
    }	
}
