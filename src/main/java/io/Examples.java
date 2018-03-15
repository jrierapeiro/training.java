package io;

import common.ICustomExamples;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Files, List, and Walk
public class Examples implements ICustomExamples {
    @Override
    public void RunExamples() {
        this.ReadingExamples();
        this.ExploringDirectories();
    }

    private void ReadingExamples(){
        Path path = Paths.get("e:","development", "debug.log");
        try(Stream<String> stream = Files.lines(path)){
            stream.filter( l -> l.contains("ERROR")).findFirst().ifPresent(System.out::println);
        }catch (IOException ioe){

        }
    }

    private  void ExploringDirectories(){
        Path path = Paths.get("e:","development", "JavaDev", "pluralsight");
        try(Stream<Path> stream = Files.walk(path)){ // Recursive; list only current directory
            stream
                    .filter( p -> p.toFile().isDirectory())
                    .forEach(System.out::println);

        }catch (IOException ioe){

        }
    }
}
