
package ar.com.aleatoria.server.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.com.aleatoria.server.db.CareerDAO;
import ar.com.aleatoria.server.model.Career;

import com.google.gson.Gson;

@Controller
@RequestMapping("career")
public class CareerServiceImpl {
    @Autowired
    CareerDAO careerDao;

    /**
     * @return json.
     * @uri http://localhost:8080/server/career/
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public @ResponseBody
    Career[] getAllCareers() {    	
        return careerDao.getAll().toArray(new Career[careerDao.getAll().size()]);
    }

    /**
     * @param newCareer: The new career to add to the database.
     * @uri http://localhost:8080/server/career/
     */
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void addCareer(@RequestBody
    Career newCareer) {
        careerDao.saveCareer(newCareer);
    }

    /**
     * @param id: The id of the career.
     * @uri http://localhost:8080/server/career/{id}
     */
    @RequestMapping(value = "/{id}")
    public @ResponseBody
    Career getCareerById(@PathVariable
    String id) {
        return (careerDao.getCareerById(id));
    }

    /**
     * @param newCareer: The newCareer to update.
     * @uri http://localhost:8080/server/career/{newCareer}
     */
    @RequestMapping(value = "/{newCareer}", method = RequestMethod.PUT)
    public void updateCareer(@RequestBody
    Career newCareer) {
        careerDao.saveCareer(newCareer);
    }

    /**
     * @param targetCareer: The targetCareer to delete.
     * @uri http://localhost:8080/server/career/{targetCareer}
     */
    @RequestMapping(value = "/{targetCareer}", method = RequestMethod.DELETE)
    public void deleteCareerById(@PathVariable
    Career targetCareer) {
        careerDao.delete(targetCareer);
    }

}
