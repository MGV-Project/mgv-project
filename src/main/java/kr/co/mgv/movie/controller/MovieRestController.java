package kr.co.mgv.movie.controller;

import kr.co.mgv.movie.service.MovieService;
import kr.co.mgv.movie.vo.Movie;
import kr.co.mgv.movie.vo.MovieLike;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/movie")
@AllArgsConstructor
public class MovieRestController {

    @Autowired
    private final MovieService movieService;

    @GetMapping("/like/{movieNo}/{userId}/{like}")
    public ResponseEntity<MovieLike> movieLike(@PathVariable("movieNo") int movieNo, @PathVariable("userId") String id, @PathVariable("like") String like){
    MovieLike movieLike = new MovieLike(id, movieNo);
    if ("true".equals(like)){
        movieService.insertMovieLike(movieLike);
        movieService.incrementMovielikes(movieNo);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieLike);
    }else {
        movieService.deleteMovieLike(movieLike);
        movieService.decrementMovielikes(movieNo);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
    }

    @GetMapping("like/{movieNo}/{userId}")
    public ResponseEntity<MovieLike> isMovieLikedByUser(@PathVariable("movieNo") int movieNo, @PathVariable("userId") String userId) {
        String res;
        MovieLike movieLike =  new MovieLike(userId,movieNo);
        if(movieService.isMovieLikedByUser(movieLike)){
            return ResponseEntity.status(HttpStatus.OK).body(movieLike);
        }else return ResponseEntity.status(HttpStatus.OK).body(new MovieLike());

    }


}