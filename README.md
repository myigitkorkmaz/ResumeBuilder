# ResumeBuilder

[![CI](https://github.com/myigitkorkmaz/ResumeBuilder/actions/workflows/ci.yml/badge.svg)](https://github.com/myigitkorkmaz/ResumeBuilder/actions/workflows/ci.yml)
![Java](https://img.shields.io/badge/Java-21%2B-orange?logo=openjdk&logoColor=white)
![Swing](https://img.shields.io/badge/UI-Swing-blue)
![PDFBox](https://img.shields.io/badge/PDF-Apache%20PDFBox-d22128)

A desktop resume builder written in Java Swing. Fill in your details section by section, pick a template, and export a formatted **PDF** or plain **TXT** resume.

## Features

- **Seven tabs:** Personal Info, Education, Work Experience, Projects, Skills, Template and Export.
- **Add, edit and remove** entries in every list section.
- **Three PDF templates:**

  | Template | Look |
  | --- | --- |
  | Classic | Times Roman, centered header, divider lines |
  | Modern | Helvetica, large left-aligned name, no dividers |
  | Minimal | Helvetica, compact and centered, no dividers |

- **Export to PDF** with [Apache PDFBox](https://pdfbox.apache.org/), or **export to TXT** for pasting into job-application forms.

## Getting started

Requires **JDK 21 or later**. PDFBox and its dependencies are already in `lib/`, so there's nothing else to install.

```bash
git clone https://github.com/myigitkorkmaz/ResumeBuilder.git
cd ResumeBuilder

# compile
javac -cp "lib/*" -d bin src/*.java

# run (macOS / Linux)
java -cp "bin:lib/*" App

# run (Windows)
java -cp "bin;lib/*" App
```

In **VS Code**, install the Java Extension Pack, open the folder and run `App.java`. The source paths and library paths are already set in `.vscode/settings.json`.

## How it works

The code uses the **Builder** pattern for putting a resume together and the **Strategy** pattern for exporting it.

```
App ──> ResumeUI (JFrame, tabs)
            │
            ▼
      ResumeBuilder ──builds──> Resume
                                  ├── PersonalInfo
                                  ├── Education[]
                                  ├── WorkExperience[]
                                  ├── Project[]
                                  ├── Skill[]
                                  └── Template
            │
            ▼
      Exporter (interface)
        ├── PDFExporter   (PDFBox, per-template Style)
        └── TXTExporter
```

| File | Role |
| --- | --- |
| `App.java` | Entry point; opens the UI |
| `ResumeUI.java` | Swing window with one tab per section |
| `ResumeBuilder.java` | Chainable builder that sits between the UI and the model |
| `Resume.java` | Aggregate model holding all sections |
| `PersonalInfo`, `Education`, `WorkExperience`, `Project`, `Skill` | Section models |
| `Template.java` | Template name, font, style and section order |
| `Exporter.java` | Common export interface |
| `PDFExporter.java` / `TXTExporter.java` | Export implementations |

## Contributors

- [@myigitkorkmaz](https://github.com/myigitkorkmaz)
- [@WesternPython24](https://github.com/WesternPython24)
