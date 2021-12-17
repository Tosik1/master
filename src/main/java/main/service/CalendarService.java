package main.service;

import main.api.response.CalendarResponse;
import main.model.custom.CountPostPerYear;
import main.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CalendarService {

    @Autowired
    private PostRepository postRepository;

    public CalendarResponse getCalendarResponse(String year){
        CalendarResponse calendarResponse = new CalendarResponse();
        calendarResponse.setYears(postRepository.findAllYears());

        if (year == null){
            year = Integer.toString(Year.now().getValue());
        }

        List<CountPostPerYear> list = postRepository.findCountForDate(year);
        Map<String, Integer> map = new HashMap<>();

        for (CountPostPerYear cus : list){
            map.put(cus.getDate(), cus.getCount());
        }
        calendarResponse.setPosts(map);

        return calendarResponse;
    }
}
