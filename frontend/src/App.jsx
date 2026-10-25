
import { useState } from 'react'
import './App.css'

function App() {
  const [emailBody, setEmailBody] = useState('')
  const [result, setResult] = useState(null)
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState('')

  async function handleAnalyze() {
    if (!emailBody.trim()) {
      setError('Please enter an email to analyze.')
      setResult(null)
      return
    }

    setLoading(true)
    setError('')
    setResult(null)

    try {
      const response = await fetch(
        'https://mailguard-2r70.onrender.com/api/email/check',
        {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json',
          },
          body: JSON.stringify({
            emailText: emailBody,
          }),
        }
      )

      if (!response.ok) {
        throw new Error(
          `Request failed with status ${response.status}`
        )
      }

      const data = await response.json()
      setResult(data)
    } catch (err) {
      setError(
        'Could not connect to the backend. Make sure Spring Boot is running.'
      )
      console.error('Email analysis error:', err)
    } finally {
      setLoading(false)
    }
  }

  return (
    <main className="app">
      <nav className="navbar">
        <h2 className="brand">MailGuard</h2>
        <span className="status">AI Email Security</span>
      </nav>

      <section className="hero">
        <p className="eyebrow">SMART EMAIL PROTECTION</p>
        <h1>Is your email <span>safe?</span></h1>
        <p className="subtitle">
          Analyze suspicious emails and identify potential spam
          before you click anything.
        </p>

        <div className="email-card">
          <label htmlFor="emailBody">Email content</label>

          <textarea
            id="emailBody"
            placeholder="Paste the email content you want to check..."
            value={emailBody}
            onChange={(event) => setEmailBody(event.target.value)}
            rows={8}
            maxLength={10000}
            disabled={loading}
          />

          <div className="card-footer">
            <span>{emailBody.length} characters</span>

            <button
              type="button"
              onClick={handleAnalyze}
              disabled={loading}
            >
              {loading ? 'Analyzing...' : 'Analyze Email →'}
            </button>
          </div>

          {error && (
            <p role="alert" className="error-message">
              {error}
            </p>
          )}

          {result && (
            <div
              className={`result-card ${
                result.spam ? 'spam-result' : 'safe-result'
              }`}
              aria-live="polite"
            >
              <h3>
                {result.spam
                  ? '⚠️ Spam Detected'
                  : '✅ No Spam Detected'}
              </h3>

              <p><strong>Result:</strong> {result.message}</p>
              <p><strong>Reason:</strong> {result.reason}</p>
            </div>
          )}
        </div>

        <p className="privacy-note">
          Your email will be analyzed only when you click the button.
        </p>
      </section>
    </main>
  )
}

export default App
