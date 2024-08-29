import './App.css';
import Navbar from './components/navbar/Navbar';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Home from './pages';
import LiveCodeEditor from './pages/editor/liveCodeEditor';
import SearchResults from './components/navbar/Search/SearchResults'; // Import the SearchResults component
import { Amplify } from 'aws-amplify';
import { Authenticator } from '@aws-amplify/ui-react';
import '@aws-amplify/ui-react/styles.css';
import awsExports from './aws-exports';
import { useState } from 'react';

Amplify.configure(awsExports);

function App() {
  const [editorData, setEditorData] = useState(null);

  return (
    <Authenticator loginMechanism={['email']}>
      <Router>
        <Navbar setEditorData={setEditorData} />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/code-editor" element={<LiveCodeEditor editorData={editorData} />} />
          <Route path="/search/:query" element={<SearchResults />} /> {/* Add this route */}
        </Routes>
      </Router>
    </Authenticator>
  );
}

export default App;
