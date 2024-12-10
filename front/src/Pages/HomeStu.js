import React, { useState, useEffect, useCallback } from 'react';

import Modal from 'react-modal';
import { useNavigate } from 'react-router-dom';

    Modal.setAppElement('#root');

    const Home = () => {
      const [studentId, setstudentId] = useState('');
      const navigate = useNavigate();
      const [userNom, setUserNom] = useState('');
      const [userPrenom, setUserPrenom] = useState('');
      const [useremail, setUseremail] = useState('');
      const [userpass, setUserpass] = useState('');

      
      const fetchData = useCallback(async () => {
        try {
          const studentId = localStorage.getItem('studentid');
          const studentNom = localStorage.getItem('studentnom');
          const studentPrenom = localStorage.getItem('studentprenom');
          
          const studentemail = localStorage.getItem('useremail');
          const studentpass = localStorage.getItem('userpassword');
          
          setstudentId(parseInt(studentId, 10));
          setUserNom(studentNom);
          setUserPrenom(studentPrenom);
          
          setUseremail(studentemail);
          setUserpass(studentpass);

        } catch (error) {
          console.error('Error fetching data:', error);
        }
      }, []);

      useEffect(() => {
        const userIdFromStorage = localStorage.getItem('userId');
        setstudentId(parseInt(userIdFromStorage, 10));
      }, []);



      useEffect(() => {
        fetchData();
      }, [studentId, fetchData]);

      const userType = localStorage.getItem('userType');
      if ( userType == null || userType !== 'Student' ) {
        navigate('/error-page');
      }
      
      return (
          <div className="home-container">
            <div style={{ display: 'flex', flexDirection: 'row' }}>
              
              <div style={{ flex: 5 }}>
                <div className="gray-box">
                  
                <h2>Student Space</h2>
              <p><strong>id:</strong> {studentId}</p>
              <p><strong>Nom:</strong> {userNom}</p>
              <p><strong>Prénom:</strong> {userPrenom}</p>
              <p><strong>Adresse email:</strong> {useremail}</p>
              <p><strong>Mot de passe:</strong> {userpass}</p>
                     
                </div>
              </div>
            </div>
        </div>
      );
    };

    export default Home;
