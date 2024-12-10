import { BrowserRouter, Routes, Route } from 'react-router-dom';

import RegisterT from './Pages/RegisterT';
import RegisterS from './Pages/RegisterS';
import RegisterA from './Pages/RegisterA';
import Login from './Pages/Login';
import HomeTea from './Pages/HomeTea';
import HomeStu from './Pages/HomeStu';
import HomeAd from './Pages/HomeAd';
import Visiteur from './Pages/Visiteur';

function App() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/admin-home" element={<HomeAd />} />
          <Route path="/teacher-home" element={<HomeTea />} />
          <Route path="/student-home" element={<HomeStu />} />
          <Route path="/registerT" element={<RegisterT/>} />
          <Route path="/registerS" element={<RegisterS/>} />
          <Route path="/registerA" element={<RegisterA/>} />
          <Route path="/login" element={<Login />} />
          <Route path="/" element={<Visiteur />} />

        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
