package com.gradingapp.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.gradingapp.bean.Result;

@Service
public class CompileAndRunService {
	
	public  Result compileAndRun(String sourceCodePath, String inputTestFilePath, String outputTestFilePath) {
        Result r = new Result();
        HashMap<String, Object> result = new HashMap() ;
        String expectedOutput = expectedOutput(outputTestFilePath);
        try {
            Boolean testCasePassed = false;
            result = runProcess("java -cp src "+sourceCodePath+"Main.java "+ inputTestFilePath );
            testCasePassed = compareResults(result.get("studentOutput").toString(), expectedOutput);
            r.setStatus((int)result.get("status"));
            r.setStudentOutput(result.get("studentOutput").toString());
            r.setExpectedOutput(expectedOutput);
            r.setErrorOutput(result.get("errorOutput").toString());
            r.setTestCasePassed(testCasePassed);
        } catch (Exception e) {
            System.out.println("failing");
        }
        return r;

    }

    private  HashMap<String, Object> runProcess(String command) throws Exception {
        HashMap<String, Object> result = new HashMap();
        Process pro = Runtime.getRuntime().exec(command);
        String studentOutput = getResult(command + " stdout:", pro.getInputStream());
        String errorOutput = getResult(command + " stderr:", pro.getErrorStream());
        pro.waitFor();
        result.put("status", pro.exitValue() );
        result.put("studentOutput", studentOutput);
        result.put("errorOutput", errorOutput);
        System.out.println(command + " exitValue() " + pro.exitValue());
        return result;
    }

    private String getResult(String cmd, InputStream ins) throws Exception {

        BufferedReader in = new BufferedReader(
                new InputStreamReader(ins));
        String output = "";
        String line = in.readLine();
        while (line != null) {
            output += line;
            line = in.readLine();
            if(line!=null)
                output +="\n";
            System.out.println(cmd + " " + line);
        }
        return output;
    }

    private  String expectedOutput(String path) {
        String content = null;
        try (Stream<String> lines = Files.lines(Paths.get(path))) {
            content = lines.collect(Collectors.joining(System.lineSeparator()));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return content;
    }

    private  Boolean compareResults(String studentOuput, String expectedOutput ) {
        return studentOuput.equals(expectedOutput);
    }
}
