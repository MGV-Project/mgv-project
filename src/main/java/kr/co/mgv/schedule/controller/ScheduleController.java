package kr.co.mgv.schedule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.mgv.schedule.dto.DateWithMovieDto;
import kr.co.mgv.schedule.service.ScheduleService;
import kr.co.mgv.theater.dto.TheaterAndDateDto;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {
	private final ScheduleService scheduleService;
	
	@GetMapping("/list")
	@ResponseBody
	public DateWithMovieDto scheduleList(int theaterNo, String date) {
		TheaterAndDateDto dto = new TheaterAndDateDto(theaterNo,date);
		return scheduleService.getScheduleList(dto);
	}
}