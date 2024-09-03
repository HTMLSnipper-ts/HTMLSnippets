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

Amplify.configure(awsExports);

function App() {
<<<<<<< Updated upstream
=======
  const [editorData, setEditorData] = useState(null);
  const [codeSnippData, setCodeSnippData] = useState(null);

>>>>>>> Stashed changes
  return (
    <Authenticator loginMechanism={['email']}>
      <Router>
        <Navbar />
        <Routes>
<<<<<<< Updated upstream
          <Route path="/" element={<Home />} />
          <Route path="/code-editor" element={<LiveCodeEditor />} />
=======
          <Route path="/" element={<Home codeSnippData={codeSnippData} setCodeSnippData={setCodeSnippData} />} />
          <Route path="/code-editor" element={<LiveCodeEditor editorData={editorData} codeSnippData={codeSnippData}/>} />
>>>>>>> Stashed changes
          <Route path="/search/:query" element={<SearchResults />} /> {/* Add this route */}
        </Routes>
      </Router>
    </Authenticator>
  );
}

export default App;
