# DigitalModulationLab

Here's the updated project report reflecting the additional features:

---

### **1. Title Page**
   - **Project Title**: Virtual Laboratory for Digital Modulation Techniques
   - **Submitted by**: Pranjali Sirari, Divyanshu Gautum
   - **Institution Name**: Indian Institute of Information Technology (IIIT) Kalyani 
   - **Date of Submission**: 31/03/2025

---

### **2. Abstract**
   This project involves the development of a virtual laboratory to simulate **Digital Modulation Techniques**. The system supports **FSK (Frequency Shift Keying)**, **BPSK (Binary Phase Shift Keying)**, and **QPSK (Quadrature Phase Shift Keying)** modulation techniques. The virtual lab allows students to input a digital data stream and configure carrier signal parameters (frequency and amplitude). The system generates and displays corresponding waveforms, including digital data, carrier signal, modulated signal, and demodulated signal. In addition, the system features quizzes, tutorials, and a user login system that allows students to view their past experiment results and quiz scores. The project also integrates a MySQL database to store experiment results and quiz scores for future reference.

---

### **3. Problem Statement**
   Design a **virtual laboratory** for performing experiments on **Digital Modulation Techniques** (FSK, BPSK, QPSK). The system should allow students to input the following:

   **Inputs:**
   - Digital data stream (via a textbox)
   - Carrier signal parameters:
     - Frequency (range 50–100 Hz)
     - Peak-to-peak amplitude (range 1V–5V)

   **Method:**
   - Select one of the modulation techniques: **FSK**, **BPSK**, or **QPSK**.

   **Buttons:**
   - **Result**: Displays the modulated signal.
   - **Reset**: Resets all input values.
   - **Demodulation**: Reverses the modulation and displays the original data.

   **Outputs:**
   - **Waveform of digital data**
   - **Carrier frequency waveform**
   - **Modulated waveform**
   - **Demodulated waveform** (when the demodulation button is clicked)

   **Objective:**
   - To create a user-friendly GUI where students can interact with the virtual lab, input values, and visualize the modulation and demodulation processes for the selected technique.

---

### **4. Algorithm**
   This section explains the algorithms used in the project:

   - **Modulation Algorithms**:
     - **FSK**: The frequency of the carrier signal is changed according to the input digital data stream.
     - **BPSK**: The phase of the carrier signal is altered based on the binary input data.
     - **QPSK**: The carrier signal's phase is shifted in four distinct phases, encoding two bits per symbol.

   - **Demodulation Algorithm**: The demodulation process recovers the original digital data from the modulated signal using the appropriate demodulation technique based on the selected modulation method.

   - **Database Interaction**: 
     - The database stores experiment data (input values and generated results), allowing users to save and retrieve past experiment results. It includes tables for user data, experiment parameters, waveform results, and quiz scores.

---

### **5. Steps/Flow of Work**

   - **Step 1: Requirements Gathering**  
     Identify the key inputs, methods, and outputs for the experiment, as well as the required components for the GUI (input fields, buttons, output sections).

   - **Step 2: Designing the GUI**  
     The GUI is built using Java Swing. It includes textboxes for the digital data stream, carrier frequency, and peak-to-peak amplitude. There are buttons for generating results, resetting values, and performing demodulation. The output section displays the waveforms for digital data, carrier frequency, modulated signal, and demodulated signal.

   - **Step 3: Implementing Modulation Techniques**  
     Implement the modulation algorithms for **FSK**, **BPSK**, and **QPSK**. Based on user input, the system generates the corresponding modulated signal.

   - **Step 4: Implementing Demodulation**  
     Implement demodulation for each modulation technique. When the user clicks the "Demodulation" button, the system retrieves and displays the original data from the modulated signal.

   - **Step 5: Database Integration**  
     Set up a MySQL database to store experiment results, including user inputs (digital data stream, carrier frequency, and amplitude), and the resulting waveforms (digital data, carrier signal, modulated signal, demodulated signal).

   - **Step 6: User Login System**  
     Implement a user login system that allows students to create accounts, log in, and securely store their past experiment results and quiz scores.

   - **Step 7: Implementing Quiz and Tutorials**  
     Add features for quizzes and tutorials to enhance learning. Students can take quizzes to assess their understanding of modulation techniques, and view tutorials to learn more about the concepts.

   - **Step 8: Testing**  
     Test the system for accuracy in generating waveforms for the selected modulation technique, ensuring the login system works, and verifying the database records and retrieves the correct experiment data and quiz scores.

---

### **6. Result**

   - The virtual laboratory successfully simulates the three modulation techniques: **FSK**, **BPSK**, and **QPSK**. 
   - Students can input the required parameters (digital data stream, carrier frequency, and peak-to-peak amplitude) and generate the corresponding waveforms for each technique.
   - The system successfully stores and retrieves experiment data from the MySQL database.
   - **Screenshots**:  
     (Include relevant screenshots of the GUI showing the input fields, waveform displays, and past experiment results stored in the database, along with quiz scores).
     login page:
     ![login](https://github.com/user-attachments/assets/14bca168-de28-4195-a66f-ce29a5444012)

      main menu:
     ![main_menu](https://github.com/user-attachments/assets/a11e4fe5-b49c-471c-ac8d-7a398fe55a02)

      Digital modulation techniques lab:
     ![Digital_Modulation_Laboratory](https://github.com/user-attachments/assets/14b45dc8-451e-49f7-9d11-a12e0d2f1f2a)

     Past experiments page:
     ![past_experiments](https://github.com/user-attachments/assets/59ab8abf-3871-40e1-b994-8c8230b181d6)

      Quiz and tutorial:
     ![Quiz_page](https://github.com/user-attachments/assets/c4f14fd6-4365-4677-ab64-8ec05b35a945)
      ![Tutorial](https://github.com/user-attachments/assets/184cc471-c6e8-4099-b510-4c6409cb404e)

     Quiz results:
     ![Quiz_scores](https://github.com/user-attachments/assets/4c913926-2e93-4193-84fa-01cc947f9591)

---

### **7. GUI (Graphical User Interface)**

   - **Design Overview**:  
     The GUI is built using **Java Swing**, designed to be intuitive and user-friendly. It follows a sleek, modern design with a **blue and white** color scheme. The layout includes input fields for entering the digital data stream, carrier frequency, and amplitude, along with buttons for generating results, resetting values, and performing demodulation.

   - **Key Features**:
     - **Input Fields**: For the digital data stream, carrier frequency, and amplitude.
     - **Buttons**: For performing actions such as **Result**, **Reset**, and **Demodulation**.
     - **Output Display**: Visualizes the waveforms of the digital data, carrier signal, modulated signal, and demodulated signal.
     - **Login/Signup**: Allows students to securely log in to the system and access their past experiment results and quiz scores.
     - **Quiz and Tutorial Section**: Students can take quizzes to test their knowledge and view tutorials to learn about digital modulation techniques.

---

### **8. Discussions**

   - **Limitations**:
     - **Limited Modulation Techniques**: Only **FSK**, **BPSK**, and **QPSK** are supported. Additional modulation techniques could be added in future versions.
     - **GUI**: While functional, the interface can be improved for better user experience and responsiveness.
     - **Quiz Feature**: The quiz feature is basic and could benefit from more diverse question types and adaptive learning pathways.

   - **Future Scope of Work**:
     - **Add More Modulation Techniques**: Future versions could include more advanced techniques such as **16-QAM**, **8-PSK**, and others.
     - **Real-Time Experiment Simulation**: Implementing a more interactive real-time simulation of the modulation process, where users can instantly see the impact of changes in parameters.
     - **PDF Export**: Allow users to export experiment results and waveforms to **PDF** or **CSV** formats for easier sharing and analysis.
     - **Enhanced Quiz System**: Expand the quiz system with more varied question formats, scoring analytics, and personalized feedback.

---

### **9. Conclusion**

   This virtual laboratory effectively simulates experiments on **Digital Modulation Techniques** (FSK, BPSK, and QPSK). It allows students to input parameters, visualize the resulting waveforms, and store experiment data in a database for future access. The addition of quizzes, tutorials, and a login system provides a comprehensive learning experience, enabling students to track their progress and revisit past experiments. The system offers an accessible and interactive way for students to learn and perform digital modulation experiments without the need for physical lab setups.

---

### **10. References**

   - **Java Documentation**: [Oracle Java Docs](https://docs.oracle.com/en/java/)
   - **MySQL Documentation**: [MySQL Docs](https://dev.mysql.com/doc/)
   - **JFreeChart**: [JFreeChart](https://www.jfree.org/jfreechart/)
   - **Books**:
     - "Modern Digital and Analog Communication Systems" by B. P. Lathi
