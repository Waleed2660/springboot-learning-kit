# Task 8 ~ Code Style and Formatting

Now we will focus on code style and formatting. This is an important aspect of writing clean and maintainable code. When 
you're working in a team, it's crucial to follow a consistent style guide to ensure that everyone can read and understand the code easily.
People with different backgrounds and experiences may have different preferences, so it's important to find a common ground.

These are some common style guides that you can follow:

### **Google Java Style Guide**
- **What it is**: Google's comprehensive style guide used across all Google Java projects
- **Key features**: 2-space indentation, 100-character line limit, specific naming conventions
- **Best for**: Teams that prefer compact, readable code with strict formatting rules
- **Use when**: Working on projects that value consistency and readability over personal preferences
- **Link**: [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)

### **Palantir Java Style Guide**
- **What it is**: A modern, opinionated formatter that prioritizes code readability and maintainability
- **Key features**: 4-space indentation, 120-character line limit, automatic import organization
- **Best for**: Teams that want minimal configuration with excellent out-of-the-box formatting
- **Use when**: You prefer a more relaxed line length and don't want to spend time tweaking formatter rules
- **Link**: [Palantir Java Style Guide](https://github.com/palantir/palantir-java-format)

### **Eclipse Java Formatter**
- **What it is**: The default formatter used by Eclipse IDE, highly customizable
- **Key features**: Configurable indentation, line wrapping, and spacing rules
- **Best for**: Teams already using Eclipse IDE or those who need extensive customization options
- **Use when**: Your team has specific formatting requirements that need fine-tuning
- **Link**: [Eclipse Formatter](https://github.com/diffplug/spotless/tree/main/plugin-gradle#eclipse-java-formatter)

### **Oracle Java Code Conventions**
- **What it is**: The original Java coding standards published by Sun/Oracle
- **Key features**: Traditional Java formatting with 4-space indentation and 80-character lines
- **Best for**: Legacy projects or teams that prefer conservative, time-tested conventions
- **Use when**: Working with older codebases or in environments that require strict adherence to Oracle standards
- **Link**: [Oracle Java Code Conventions](https://www.oracle.com/java/technologies/javase/codeconventions-contents.html)

---

## Which Style Guide to Choose?

Choosing a style guide depends on your team's preferences and the project's requirements. I've worked with different formatters
over the years, and each has its strengths. Google's Java Styl Guide is most commonly used in the industry, but it comes with
100 character line limit, which can be restrictive & would always cause line breaks in the middle of a method call. This can result
in too much vertical real estate being used for a single method call, making it harder to read.

The 2-line indentation in Google's style guide can also be a bit cramped, especially for complex code structures. I'm personally
not a big fan of 2-line indentation.


**My Recommendation**: I'll be using **⭐️ Palantir Java Format** in this project because it:
- Requires minimal configuration with Spotless
- Allows 120-character lines, which is more readable than 80 or 100
- Provides 4-line indentation, which is easier on the eyes
- Produces clean, readable code
- Has excellent IDE integration

---

## Spotless 

Spotless is a tool that can help you format your code according to a specific style guide. It can be integrated into 
your build process, and it can automatically format your code when you run the build. This way, you can ensure that your 
code is always formatted correctly, and you don't have to worry about formatting it manually. You can find the
latest version of Spotless [here](https://plugins.gradle.org/plugin/com.diffplug.gradle.spotless)

To use Spotless, you need to add it to your `build.gradle` file. Here is an example of how to do that:

```groovy
plugins {
    id 'java'
    id 'com.diffplug.spotless' version '7.0.4'
}

// rest of your properties and dependencies
// ........................................

spotless {
    java {
        palantirJavaFormat()
        // or use googleJavaFormat() for Google style guide
    }
}
```

After adding Spotless to your project, you can run the following command to format your code:

```bash
  ./gradlew spotlessApply
```

### Automate Spotless 

You can automate the formatting process by adding a task to your `build.gradle` file that runs Spotless before the build.
This way, every time you run the `build` task, Spotless will automatically format your code before the build starts.

Here is an example of how to do that:

```groovy
tasks.named('build') {
    dependsOn 'spotlessApply'
}
```

If you run the `build` task now, it will automatically format your code before building the project:

```bash
  ./gradlew build
```

You should have all of your files formatted according to Palantir style guide & ready to commit them to your repository.
