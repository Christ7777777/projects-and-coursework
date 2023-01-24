package com.example.demo.trivia;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TriviaController {
    private ArrayList<Trivia> triviaQuestions = new ArrayList<>();

    @GetMapping({"/trivia"})
    @ResponseBody
    public ArrayList<Trivia> getQuestions() {
        return triviaQuestions;
    }

    @PostMapping({"/trivia"})
    @ResponseBody
    public String createQuestion(@RequestBody Trivia question) {
        System.out.println(question);
        this.triviaQuestions.add(question);
        return "Successfully saved question with index of " + triviaQuestions.indexOf(question) + "!";
    }

    @GetMapping({"/trivia/{index}"})
    @ResponseBody
    public Trivia getStudentInfo(@PathVariable int index) {
        return (Trivia)this.triviaQuestions.get(index);
    }

    @PutMapping({"/trivia/{index}"})
    @ResponseBody
    public String updateStudentInfo(@PathVariable int index, @RequestBody Trivia q) {
        this.triviaQuestions.set(index, q);
        return "Trivia questions at index " + index + " successfully updated!";
    }

    @DeleteMapping({"/trivia/{index}"})
    @ResponseBody
    public String updateStudentInfo(@PathVariable int index) {
        this.triviaQuestions.remove(index);
        return "Trivia questions at index " + index + " successfully removed from trivia list!";
    }

    @GetMapping({"/trivia/q"})
    @ResponseBody
    public String getQuestion(@RequestParam int index) {
        return this.triviaQuestions.get(index).getQuestion();
    }

    @GetMapping({"trivia/a"})
    @ResponseBody
    public String getAnswer(@RequestParam int index) {
        return this.triviaQuestions.get(index).getAnswer();
    }
}
