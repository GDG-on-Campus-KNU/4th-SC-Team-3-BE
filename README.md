<p align='center'>[2025 APAC Solution Challenge]</p>

<div align='center'>
<img width="256" alt="sbArtboard 2" src="https://github.com/user-attachments/assets/4a8a4b7b-d477-42b6-afb1-e81337c31e9a" />
</div>

<h1 align="center">PIPY: Flow UX-Based Generative AI Integration Platform </br> </h1>

<div align='center'>
<img width="968" alt="image" src="https://github.com/user-attachments/assets/49799c5c-f56d-45cf-9ea7-b6dae145ea11" />

</br> Our platform offers a visual, node-based workflow that lets users seamlessly discover, connect, and use multiple generative AI tools. <br/> With an integrated marketplace and intuitive prompt analysis, anyone can build custom multi-tool workflows without writing code.

🔗 **Prototype Link**: [https://pipy.me](https://pipy.me)  
📺 **YouTube**: [2025 APAC Solution Challenge: PIPY](https://www.youtube.com/watch?v=uC_2KQo8bQc)

[Repositories] </br>
🖼️ [Frontend](https://github.com/GDG-on-Campus-KNU/4th-SC-Team-3-FE) </br>
🛠️ [Backend](https://github.com/GDG-on-Campus-KNU/4th-SC-Team-3-BE)</br>
🤖 [AI / Model](https://github.com/GDG-on-Campus-KNU/4th-SC-Team-3-AI)</br>
</div>

## Contents

1. [Overview](#overview)
2. [Propsed Solutions](#propsed-solutions)
3. [Main Features](#main-features)
4. [Architecture](#architecture)
5. [Skills](#skills)
6. [Screens](#screens)
7. [Future Development](#future-development)
8. [Closing](#closing)

## Overview

### Imbalance between supply and demand in the generative AI tools trade market

As of now, over 12,000 generative AI services have been launched by a wide range of providers, with around 50 new tools emerging daily and 30 quietly disappearing without notice (based on generative AI directories like OpenTools and Futurepedia). Despite this rapid proliferation, there is a clear imbalance: the AI Index Report 2024 shows that while 5% of users actively explore more than 10 tools, a majority (95%) stick to just one or two main platforms.

To bridge this gap, we propose an AI Marketplace that brings together diverse tools in one simple platform, demonstrates how they can integrate seamlessly for greater value, and helps users effortlessly discover, use, and expand their AI toolsets.

## Propsed Solutions

<img width="600" alt="image" src="https://github.com/user-attachments/assets/e8893612-4aa1-4cd2-bdef-00a6bd814b00" />

</br> Within our intuitive, visual interface, users can easily discover, compare, and chain multiple AI tools, analyze and simplify complex prompts, and produce rich, tailored outputs. Our integrated workflow empowers everyone—from curious beginners to seasoned creators—to add new tools instantly and build custom, multi-tool pipelines without ever touching raw code.

## Main Features

1. **Node-Based Workflow**  
   Users can visualize and create workflows through a node-based system, enabling seamless integration of multiple generative AI tools in a clear and intuitive way, rather than relying on simple text input.

2. **Prompt Analysis**  
   Helps users understand complex prompts by breaking them down into clear, interpretable components.

<img width="600" alt="image" src="https://github.com/user-attachments/assets/7c990bd5-aa4b-4a12-9706-8ac230f80db7" />

3. **Generation**  
   Users can combine multiple prompts to generate what they want, tailored to their creative vision.

<img width="600" alt="image" src="https://github.com/user-attachments/assets/336b579c-6106-42c2-802a-e5677a50f73d" />

4. **AI Tool Marketplace**  
   A built-in marketplace allows users to purchase or add new AI tools, enabling customization and expansion of their creative workflows.

## Architecture

<img width="600" alt="image" src="https://github.com/user-attachments/assets/a5247475-28fe-4a4b-bbb7-4811687ad2f3" />

## Skills

### Backend
| Technology                         | Description                                                             |
|------------------------------------|-------------------------------------------------------------------------|
| Java & Spring Boot                 | Handles data processing                                                 |
| MySQL                              | Stores node and project information                                     |
| Google Cloud Storage               | Saves generated output data                                             |
| GCP Compute Engine                 | Deployment on scalable virtual machines                                 |

### Frontend

| Technology                         | Description                                                             |
|------------------------------------|-------------------------------------------------------------------------|
| TypeScript & React.js              | Builds dynamic and component-based user interfaces                      |
| Vite.js                            | Fast development and optimized build performance                        |
| Tailwind CSS                       | Rapid UI development with utility-first classes                         |
| React Flow                         | Node-based interactive workflow editor                                  |
| Google Firebase                    | Fast and simple frontend deployment                                     |

### AI

| Technology                         | Description                                                             |
|------------------------------------|-------------------------------------------------------------------------|
| Gemini API                         | Generates images & analyzes prompts                                     |
| Python & FastAPI                   | Lightweight, high-performance AI service endpoints                      |
| GCP Compute Engine                 | Deployment for scalable AI services                                     |

## Screens

### 📊 Dashboard

<img width="600" alt="image" src="https://github.com/user-attachments/assets/97a5ac02-9450-455e-a505-03b24ea1e39f" />
<img width="600" alt="image" src="https://github.com/user-attachments/assets/e0e59ab0-bf93-4e10-8c19-a9839de74205" />

You can create the project directly from the dashboard:

<img width="600" alt="image" src="https://github.com/user-attachments/assets/50f072e0-ceee-446c-8096-5b7b90e1ee5b" />

### 🎨 Canvas

<img width="600" alt="image" src="https://github.com/user-attachments/assets/520a3360-a9ce-4615-b998-4f917697c333" />

Within the canvas, you can visually construct AI workflows by creating nodes and connecting them via edges.

<img width="600" alt="image" src="https://github.com/user-attachments/assets/a8122edc-34b8-4fe2-a63a-6385b769c4cb" />

### 🔐 Sign In

<img width="600" alt="image" src="https://github.com/user-attachments/assets/af991374-9d07-4e1d-a392-970f1e260603" />

## Future Development

1. **Expansion of Generative AI Tools**
   
While the current MVP focuses on image generation, future updates will support various output types such as video, audio, and more by integrating a wider range of generative AI tools.

2. **AI Tool Marketplace**
   
We plan to introduce a marketplace where various companies and startups can offer API access to their own generative AI tools. Users will be able to purchase and integrate these tools into their custom workflows.

3. **Recurrent AI Connection Across Modality**
   
Currently, outputs from generative AI tools cannot be used as inputs for other nodes. We aim to enable seamless data flow between nodes to support more complex and continuous workflows.

## Closing

This is the core of PIPY: seamless integration and connection. We envision a platform where anyone, regardless of technical expertise, can effortlessly build, expand, and continuously refine their generative AI workflows. By providing intuitive visual tools, easy integration, and a dynamic marketplace, PIPY empowers creativity, innovation, and exploration in the rapidly evolving landscape of generative AI.

Join us in shaping a future where creativity and technology seamlessly converge. ✨
