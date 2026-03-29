
IMPORTANT Note: Due to a technical git force-push error on March 29, the original commit history was lost. This assignment was originally completed and uploaded on its original deadline.

# Software Quality Evaluation Tool (ISO/IEC 25010 & 25023)

## Project Description

This project implements a **software quality evaluation tool** using **Object-Oriented Programming and Java Collections**.

The system evaluates software products according to the **ISO/IEC 25010 Software Product Quality Model** and uses **ISO/IEC 25023 measurement metrics** to calculate quality scores.

The application calculates:

* Metric scores (1–5 scale)
* Quality dimension scores
* Overall system quality score
* Weakest quality characteristic
* Quality gap analysis

---

# ISO/IEC 25010 Quality Characteristics

The following quality characteristics are evaluated:

1. Functional Suitability
2. Reliability
3. Performance Efficiency
4. Maintainability

---

# ISO/IEC 25023 Metrics Used

| Characteristic         | Metric                        | Direction | Unit        |
| ---------------------- | ----------------------------- | --------- | ----------- |
| Functional Suitability | Functional Completeness Ratio | Higher    | %           |
| Functional Suitability | Functional Correctness Ratio  | Higher    | %           |
| Reliability            | Availability Ratio            | Higher    | %           |
| Reliability            | Defect Density                | Lower     | defect/KLOC |
| Performance Efficiency | Response Time                 | Lower     | ms          |
| Performance Efficiency | CPU Utilisation               | Lower     | %           |
| Maintainability        | Test Coverage Ratio           | Higher    | %           |
| Maintainability        | Cyclomatic Complexity         | Lower     | score       |

---

# Score Calculation

If **higher values indicate better quality**:

score = 1 + (measuredValue − minValue) / (maxValue − minValue) × 4

If **lower values indicate better quality**:

score = 5 − (measuredValue − minValue) / (maxValue − minValue) × 4

Constraints:

* Score range: **1–5**
* Rounded to **nearest 0.5**

---

# Technologies Used

* Java
* Object-Oriented Programming
* ArrayList
* HashMap

---

# Project Structure

```
Lab2-sw-quality
│
├── src
│   ├── Criterion.java
│   ├── QualityDimension.java
│   ├── SWSystem.java
│   ├── SWSystemData.java
│   └── Main.java
│
├── README.md
└── .gitignore
```

---

# Example Output

```
SOFTWARE QUALITY EVALUATION REPORT

System: ShopSphere v3.2.1 (Web)

Functional Suitability Score: 4.5
Reliability Score: 4.5
Performance Efficiency Score: 4.0
Maintainability Score: 3.5

Overall Quality Score: 4.1

Weakest Characteristic: Maintainability
Gap: 1.5
```
