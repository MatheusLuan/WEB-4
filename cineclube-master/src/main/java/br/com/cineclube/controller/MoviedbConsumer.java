package br.com.cineclube.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cineclube.tmdb.model.MovieTMDB;
import br.com.cineclube.tmdb.model.WrapperMovieSearch;
import br.com.cineclube.tmdb.service.MoviedbService;

@RestController // RestController ou Controller estamos expondo via url
@RequestMapping("/api/v1")
public class MoviedbConsumer {
	
    @Autowired // injecao de dependencia - isolamos a logica de negocio nessa classe Service
	MoviedbService apiService;
    
    /**
     * TESTE pela nossa API:
     * http :8080/api/v1/MovieTMDB/550
     * 
     * Chamar diretamenta a API do themoviedb
     * http https://api.themoviedb.org/3/movie/550?api_key=d1da20fbfa65312b857fb7b517bf855c 
     */
    @RequestMapping("/MovieTMDB/{id}")
    public MovieTMDB getMovieById(@PathVariable Long id) {
    	
        return apiService.getMovieById(id);
    }
    
    /**
     * TESTE pela nossa API:
     * http :8080/api/v1/search title==avatar year==2009
     * 
     * Chamada direta:
     * http https://api.themoviedb.org/3/search/movie?api_key=d1da20fbfa65312b857fb7b517bf855c query==avatar year==2009
     * 
     * Retorna uma lista de filmes
     */
    @GetMapping("/search")
    public WrapperMovieSearch searchMovie(@RequestParam String title, @RequestParam Integer year){
    	
    	// chama a MovieService:    	
    	return apiService.searchMovie(title, year);
    	
    }
    
    /*
     * retorna somente o primeiro filme da lista
     */
    @GetMapping("/search1")
    public MovieTMDB searchOneMovie(@RequestParam String title, @RequestParam Integer year){
    	
    	// chama a MovieService:
    	return apiService.searchOneMovie(title, year);

    }
    
    
}
