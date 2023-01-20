# Area calculator
This project is a simple area calculator that can calculate the area of various shapes, including quadrilaterals and triangles. It is written in Java and utilizes object-oriented programming principles. This program employs the concept of polymorphism to calculate different shapes.

## Getting Started
To run this project, you will need to have Java installed on your computer. You can download the latest version of Java from the [Oracle](https://www.oracle.com/java/technologies/downloads/ "Java Software") website.

Once you have Java installed, you can clone this repository using the following command:
```
java git clone https://github.com/anikethgawali/area-calculator.git
```

Import the SWT as an external dependency in your environment. If you are using Eclipse, you will find the instructions on importing SWT in your library on [Eclipse](https://www.eclipse.org/swt/eclipse.php "Eclipse SWT  Import") website

You can then navigate to the `JavaMiniProjectPro+` directory and compile the code using the following command:
```
javac Main.java
```

Finally, you can run the program using the following command:
```
java Main
```

The program will prompt you to enter the points you want to calculate the area for and identify the shape. Once you have entered the coordinates,  select Tools > Calculate. It will display the shape on canvas adjacent to the table alongside area and geometry name.

## Features
- The program supports the calculation of area for 12 different shapes of quadrilateral and triangle.
- The program identifies and removes the collinear points using Convex Hull algorithm.
- The program utilizes polymorphism and object-oriented programming principles to separate the calculations for each shape into its own classes.
- The program also includes error handling to ensure that only valid input is accepted.
- The program can also handle -ve coordinates.

## Future Improvements
- Add support for more shapes, polygons, and special types of polygons.
- Allow users to switch between different units of measurement (e.g. square feet, square meters).

## Contributing
If you would like to contribute to this project, please fork the repository and make a pull request.

## License
This project is licensed under the MIT License - see the [LICENSE](https://github.com/anikethgawali/area-calculator/blob/master/LICENSE "License") file for details.
