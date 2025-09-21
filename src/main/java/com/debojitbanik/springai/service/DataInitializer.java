package com.debojitbanik.springai.service;

import com.debojitbanik.springai.entity.Movie;
import com.debojitbanik.springai.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private MovieRepository movieRepository;
    @Override
    public void run(String... args) {
        List<Movie> movies;
        if (movieRepository.findById(1L).isEmpty()){
            movies = Arrays.asList(
                    new Movie("Inception", "Sci-Fi", "English", Arrays.asList("Leonardo DiCaprio", "Elliot Page"), "A thief enters dreams and steals secrets."),
                    new Movie("Avatar: Fire and Ash", "Sci-Fi", "English", Arrays.asList("Sam Worthington", "Zoe Saldaña"), "Jake and Neytiri's family faces new threats after Neteyam's death."),
                    new Movie("Tron: Ares", "Sci-Fi", "English", Arrays.asList("Jared Leto", "Evan Peters"), "A digital program enters the real world on a dangerous mission."),
                    new Movie("Predator: Badlands", "Action", "English", Arrays.asList("Elle Fanning", "Dimitrius Schuster-Koloamatangi"), "A predator outcast teams up with an unlikely ally."),
                    new Movie("Mickey 17", "Sci-Fi", "English", Arrays.asList("Robert Pattinson", "Steven Yeun"), "An expendable explores ice planets for human colonization."),
                    new Movie("Jurassic World: Rebirth", "Adventure", "English", Arrays.asList("Scarlett Johansson", "Jonathan Bailey"), "Scientists extract DNA from prehistoric creatures."),
                    new Movie("The Electric State", "Drama", "English", Arrays.asList("Millie Bobby Brown", "Chris Pratt"), "A teen travels with a robot to find her brother."),
                    new Movie("Mercy", "Thriller", "English", Arrays.asList("Chris Pratt", "Rebecca Ferguson"), "A detective proves his innocence in a crime."),
                    new Movie("Superman", "Action", "English", Arrays.asList("David Corenswet", "Rachel Brosnahan"), "Superman reconciles Kryptonian heritage with human life."),
                    new Movie("War of the Worlds", "Sci-Fi", "English", Arrays.asList("Tom Cruise", "Dakota Fanning"), "Earth faces a colossal alien invasion."),
                    new Movie("Companion", "Horror", "English", Arrays.asList("Taylor Russell", "Lukas Gage"), "A weekend getaway turns terrifying as secrets emerge."),
                    new Movie("Bugonia", "Thriller", "English", Arrays.asList("Emma Stone", "Jesse Plemons"), "Two men kidnap a CEO, believing her to be an alien."),
                    new Movie("28 Years Later", "Horror", "English", Arrays.asList("Cillian Murphy", "Naomi Harris"), "Survivors of the rage virus face mutated threats."),
                    new Movie("Good Fortune", "Comedy", "English", Arrays.asList("Seth Rogen", "Keke Palmer"), "An inept angel meddles in the lives of two humans."),
                    new Movie("The Bride", "Drama", "English", Arrays.asList("Jake Gyllenhaal", "Jessie Buckley"), "Frankenstein creates a companion from a murdered woman."),
                    new Movie("Frankenstein", "Drama", "English", Arrays.asList("Benedict Cumberbatch", "Jonny Lee Miller"), "A scientist brings a creature to life in a tragic experiment."),
                    new Movie("Lilo & Stitch", "Animation", "English", Arrays.asList("Chris Sanders", "Daveigh Chase"), "A Hawaiian girl befriends a runaway alien."),
                    new Movie("Elio", "Animation", "English", Arrays.asList("Yonas Kibreab", "America Ferrera"), "Elio goes on a cosmic adventure meeting alien lifeforms."),
                    new Movie("The Fantastic Four: First Steps", "Superhero", "English", Arrays.asList("Pedro Pascal", "Vanessa Kirby"), "Fantastic Four defend Earth from Galactus."),
                    new Movie("Thunderbolts*", "Action", "English", Arrays.asList("Florence Pugh", "Sebastian Stan"), "A team of antiheroes confronts dark pasts."),
                    new Movie("Primitive War", "Action", "English", Arrays.asList("Chris Pine", "Idris Elba"), "Soldiers discover monsters in a remote jungle."),
                    new Movie("Brick", "Mystery", "English", Arrays.asList("Joseph Gordon-Levitt", "Nora Zehetner"), "A mysterious brick wall traps apartment residents."),
                    new Movie("Mirai", "Adventure", "Japanese", Arrays.asList("Moka Kamishiraishi", "Haruo Hosoya"), "A warrior must protect scriptures that grant divine power."),
                    new Movie("Ash", "Drama", "English", Arrays.asList("Ron Perlman", "Simu Liu"), "Survivors battle a deadly virus in a ruined metropolis."),
                    new Movie("The Gorge", "Thriller", "English", Arrays.asList("Anya Taylor-Joy", "Miles Teller"), "Two guards watch a classified gorge facing moral dilemmas."),
                    new Movie("The Running Man", "Action", "English", Arrays.asList("Arnold Schwarzenegger", "Maria Conchita Alonso"), "Contestants fight for survival on a deadly game show."),
                    new Movie("Spaceballs 2", "Comedy", "English", Arrays.asList("Mel Brooks", "Bill Pullman"), "A parody sequel to galactic adventures."),
                    new Movie("Afterburn", "Action", "English", Arrays.asList("Matt Smith", "Nathalie Emmanuel"), "Pilots compete in post-apocalyptic races."),
                    new Movie("The Assessment", "Drama", "English", Arrays.asList("Carey Mulligan", "Hugh Grant"), "Couples undergo emotional tests in a dystopian society."),
                    new Movie("Companions of Light", "Fantasy", "Spanish", Arrays.asList("Javier Bardem", "Penélope Cruz"), "Ancient guardians fight darkness in mythical lands."),
                    new Movie("Project Hail Mary", "Sci-Fi", "English", Arrays.asList("Ryan Gosling", "Emma Stone"), "A lone astronaut races to save humanity."),
                    new Movie("Alien: Earth", "Sci-Fi", "English", Arrays.asList("Sigourney Weaver", "Michael Fassbender"), "Earth faces a new threat from the xenomorphs."),
                    new Movie("M3GAN 2.0", "Horror", "English", Arrays.asList("Allison Williams", "Violet McGraw"), "A robotic doll returns to save victims from weaponized rivals."),
                    new Movie("The Shrouds", "Drama", "English", Arrays.asList("Vincent Cassel", "Guy Pearce"), "Families mourn and uncover secrets in futuristic cemeteries."),
                    new Movie("Love Me", "Romance", "English", Arrays.asList("Kristen Stewart", "Steven Yeun"), "A love story spanning human and robot generations."),
                    new Movie("Arco", "Adventure", "French", Arrays.asList("Juliette Binoche", "Omar Sy"), "Explorers seek the hidden city of Arco."),
                    new Movie("Thunderstruck", "Action", "English", Arrays.asList("Chris Hemsworth", "Tessa Thompson"), "Thunder gods battle to save their realms."),
                    new Movie("Palm Springs", "Comedy", "English", Arrays.asList("Andy Samberg", "Cristin Milioti"), "Two strangers get stuck in a time loop during a wedding."),
                    new Movie("Underwater", "Thriller", "English", Arrays.asList("Kristen Stewart", "T.J. Miller"), "Researchers confront monsters deep beneath the ocean."),
                    new Movie("Tenet", "Sci-Fi", "English", Arrays.asList("John David Washington", "Robert Pattinson"), "Agents manipulate time to prevent world catastrophe."),
                    new Movie("Dune: Part Two", "Sci-Fi", "English", Arrays.asList("Timothée Chalamet", "Zendaya"), "Paul Atreides leads a revolution on Arrakis."),
                    new Movie("Furiosa: A Mad Max Saga", "Action", "English", Arrays.asList("Anya Taylor-Joy", "Chris Hemsworth"), "Furiosa fights for survival in post-apocalyptic wastelands."),
                    new Movie("The Substance", "Thriller", "English", Arrays.asList("Demi Moore", "Margaret Qualley"), "A mysterious substance gives individuals supernatural power."),
                    new Movie("Five Nights at Freddy's 2", "Horror", "English", Arrays.asList("Josh Hutcherson", "Elizabeth Lail"), "Animatronics wreak havoc on security guards."),
                    new Movie("Project Icarus", "Sci-Fi", "English", Arrays.asList("Tom Holland", "Zendaya"), "Scientists attempt a dangerous solar experiment."),
                    new Movie("The Occupant", "Thriller", "English", Arrays.asList("Oscar Isaac", "Jessica Chastain"), "A couple faces strange occurrences in their new home."),
                    new Movie("Invasion", "Sci-Fi", "English", Arrays.asList("Sam Neill", "Miranda Otto"), "Aliens invade and humans fight back for survival."),
                    new Movie("Brick Lane", "Drama", "English", Arrays.asList("Tannishtha Chatterjee", "Satish Kaushik"), "A woman strives for independence in London."),
                    new Movie("Rim of the World", "Adventure", "English", Arrays.asList("Jack Gore", "Miya Cech"), "Kids save the world during a summer camp alien invasion.")
            );
            movieRepository.saveAll(movies);
            System.out.println(movies.size() + " movies initialized in the database.");

        }
    }
}
