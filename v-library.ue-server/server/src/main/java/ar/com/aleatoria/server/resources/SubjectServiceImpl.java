
package ar.com.aleatoria.server.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.aleatoria.server.db.SubjectDAO;
import ar.com.aleatoria.server.model.Career;
import ar.com.aleatoria.server.model.Subject;

import com.google.gson.Gson;

@Controller
@RequestMapping("subject")
public class SubjectServiceImpl {
    @Autowired
    SubjectDAO subjectDao;

    /**
     * @return json.
     * @uri http://localhost:8080/server/subject/
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    Subject[] getAllSubjects() {
    	return subjectDao.getAll().toArray(new Subject[subjectDao.getAll().size()]);
    }

    /**
     * @param newSubject: The new subject to add to the database.
     * @uri http://localhost:8080/server/subject/
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void addSubject(@RequestBody
    Subject newSubject) {
        subjectDao.saveSubject(newSubject);
    }

    /**
     * @param id: The id of the subject.
     * @uri http://localhost:8080/server/subject/{id}
     */
    @RequestMapping(value = "/{id}")
    public @ResponseBody
    Subject getSubjectById(@PathVariable
    String id) {
        return subjectDao.getSubjectById(id);
    }

    /**
     * @param newSubject: The newSubject to update.
     * @uri http://localhost:8080/server/subject/{newSubject}
     */
    @RequestMapping(value = "/{newSubject}", method = RequestMethod.PUT)
    public void updateSubject(@RequestBody
    Subject newSubject) {
        subjectDao.saveSubject(newSubject);
    }

    /**
     * @param targetSubject: The targetSubject to delete.
     * @uri http://localhost:8080/server/subject/{targetSubject}
     */
    @RequestMapping(value = "/{targetSubject}", method = RequestMethod.DELETE)
    public @ResponseBody
    void deleteSubjectById(@PathVariable
    Subject targetSubject) {
        subjectDao.delete(targetSubject);
    }
}
