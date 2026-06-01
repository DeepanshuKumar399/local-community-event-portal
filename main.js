console.log("Welcome to the Community Portal");

const PORTAL_NAME = "Local Community Event Portal";
const PORTAL_OPEN_DATE = "2025-01-01";
let totalSeats = 100;

console.log(`Portal: ${PORTAL_NAME} | Open since: ${PORTAL_OPEN_DATE} | Seats: ${totalSeats}`);

class CommunityEvent {
  constructor(id, name, category, location, date, seats, fee) {
    this.id       = id;
    this.name     = name;
    this.category = category;
    this.location = location;
    this.date     = new Date(date);
    this.seats    = seats;
    this.fee      = fee;
  }
}

CommunityEvent.prototype.checkAvailability = function () {
  return this.seats > 0 && this.date >= new Date();
};

function logEventDetails(event) {
  Object.entries(event).forEach(([key, value]) => {
    console.log(`  ${key}: ${value}`);
  });
}

const eventsData = [
  new CommunityEvent(1, "Annual City Fair",   "fair",     "City Park",        "2025-08-15", 80,  50),
  new CommunityEvent(2, "Sports Day",         "sports",   "Sports Complex",   "2025-08-22", 120, 0),
  new CommunityEvent(3, "Cultural Night",     "cultural", "Town Hall",        "2025-08-30", 60,  100),
  new CommunityEvent(4, "Health Camp",        "health",   "Community Centre", "2025-09-05", 200, 0),
  new CommunityEvent(5, "Food Festival",      "food",     "Main Square",      "2025-09-12", 90,  80),
  new CommunityEvent(6, "Music Workshop",     "music",    "Arts Centre",      "2025-09-18", 40,  60),
  new CommunityEvent(7, "Workshop on Baking", "food",     "Community Hall",   "2025-09-25", 30,  40),
  new CommunityEvent(8, "Jazz Evening",       "music",    "City Park",        "2025-10-02", 50,  70),
  new CommunityEvent(9, "Past Marathon",      "sports",   "City Road",        "2024-01-10", 0,   0),
];

function makeCategoryCounter() {
  const counts = {};
  return {
    increment(category) {
      counts[category] = (counts[category] || 0) + 1;
    },
    getCount(category) {
      return counts[category] || 0;
    },
    getAll() {
      return { ...counts };
    }
  };
}
const categoryCounter = makeCategoryCounter();

function filterEventsByCategory(events, predicate) {
  return events.filter(predicate);
}

function addEvent(eventsArray, eventObj) {
  eventsArray.push(eventObj);
  renderEvents(eventsArray);
}

function registerUser(event, userName) {
  try {
    if (!event.checkAvailability()) throw new Error("Event is full or has passed.");
    event.seats--;
    categoryCounter.increment(event.category);
    console.log(`✅ ${userName} registered for "${event.name}". Seats left: ${event.seats}`);
    return true;
  } catch (err) {
    console.error(`Registration error: ${err.message}`);
    return false;
  }
}

function getEventSummary({ name, category, location, fee }) {
  return `${name} | ${category} | ${location} | Fee: ₹${fee}`;
}

function cloneAndFilter(events, predicate) {
  const cloned = [...events];
  return cloned.filter(predicate);
}

function formatCardTitle(event) {
  return event.name.startsWith("Workshop")
    ? event.name
    : `${event.category.charAt(0).toUpperCase() + event.category.slice(1)}: ${event.name}`;
}

function renderEvents(events) {
  const container = document.getElementById("eventsContainer");
  if (!container) return;
  container.innerHTML = "";

  const validEvents = events.filter(e => {
    if (!e.checkAvailability()) return false;
    return true;
  });

  if (validEvents.length === 0) {
    container.innerHTML = `<p style="color:#888;">No events match your search.</p>`;
    return;
  }

  validEvents.forEach(event => {
    const card = buildEventCard(event);
    container.appendChild(card);
  });
}

function buildEventCard(event) {
  const card = document.createElement("div");
  card.className = "eventCard";
  card.dataset.id = event.id;

  const title   = formatCardTitle(event);
  const summary = getEventSummary(event);

  card.innerHTML = `
    <h3>${title}</h3>
    <p>📅 ${event.date.toDateString()} &nbsp;|&nbsp; 📍 ${event.location}</p>
    <p>💺 Seats: <span id="seats-${event.id}">${event.seats}</span>
       &nbsp;|&nbsp; 💰 Fee: ₹${event.fee}</p>
    <p style="font-size:12px;color:#888;">${summary}</p>
    <div style="display:flex;gap:8px;margin-top:10px;">
      <button class="cta-button" id="reg-btn-${event.id}"
        onclick="handleRegisterClick(${event.id})">Register</button>
      <button class="cta-button" style="background:#e53935;"
        onclick="handleCancelClick(${event.id})">Cancel</button>
    </div>
    <p id="reg-msg-${event.id}" style="font-size:13px;margin-top:6px;"></p>
  `;
  return card;
}

function handleRegisterClick(eventId) {
  const event    = eventsData.find(e => e.id === eventId);
  if (!event) return;

  const userName = document.getElementById("quickName")?.value.trim() || "Guest";
  const success  = registerUser(event, userName);
  const msg      = document.getElementById(`reg-msg-${eventId}`);
  const seatEl   = document.getElementById(`seats-${eventId}`);

  if (success) {
    if (seatEl) seatEl.textContent = event.seats;
    if (msg)   { msg.textContent = `✅ Registered! Seats left: ${event.seats}`; msg.style.color = "green"; }
    if (window.$) $(`#reg-msg-${eventId}`).hide().fadeIn(400);
    if (event.seats === 0) renderEvents(getCurrentFilteredEvents());
  } else {
    if (msg) { msg.textContent = "❌ Registration failed — event full or past."; msg.style.color = "red"; }
  }
}

function handleCancelClick(eventId) {
  const event  = eventsData.find(e => e.id === eventId);
  if (!event) return;
  event.seats++;
  const seatEl = document.getElementById(`seats-${eventId}`);
  const msg    = document.getElementById(`reg-msg-${eventId}`);
  if (seatEl) seatEl.textContent = event.seats;
  if (msg)   { msg.textContent = "↩️ Registration cancelled."; msg.style.color = "#888"; }
  console.log(`Cancelled registration for "${event.name}". Seats: ${event.seats}`);
}

function handleCategoryFilter(value) {
  const filtered = value === "all"
    ? eventsData
    : filterEventsByCategory(eventsData, e => e.category === value);
  renderEvents(filtered);
  window._currentFilter = value;
}

function getCurrentFilteredEvents() {
  const f = window._currentFilter || "all";
  return f === "all" ? eventsData : eventsData.filter(e => e.category === f);
}

function handleSearchKeydown(e) {
  const query    = e.target.value.toLowerCase();
  const filtered = cloneAndFilter(eventsData, ev =>
    ev.name.toLowerCase().includes(query) ||
    ev.location.toLowerCase().includes(query)
  );
  renderEvents(filtered);
}

async function fetchEventsFromAPI() {
  const spinner  = document.getElementById("loadingSpinner");
  const errorBox = document.getElementById("fetchError");
  if (spinner)  spinner.style.display = "block";
  if (errorBox) errorBox.textContent  = "";

  try {
    const response = await fetch("https://jsonplaceholder.typicode.com/posts?_limit=3");
    if (!response.ok) throw new Error(`HTTP error: ${response.status}`);
    const data = await response.json();

    const apiEvents = data.map((item, i) =>
      new CommunityEvent(100 + i, item.title.slice(0, 30), "community", "Online", "2025-11-01", 20, 0)
    );

    apiEvents.forEach(e => eventsData.push(e));
    renderEvents(eventsData);
    console.log("✅ Events fetched from API:", apiEvents.length);
  } catch (err) {
    console.error("Fetch error:", err.message);
    if (errorBox) errorBox.textContent = `⚠️ Could not load remote events: ${err.message}`;
  } finally {
    if (spinner) spinner.style.display = "none";
  }
}

function fetchEventsPromise() {
  return fetch("https://jsonplaceholder.typicode.com/posts?_limit=2")
    .then(res => {
      if (!res.ok) throw new Error(`HTTP ${res.status}`);
      return res.json();
    })
    .then(data => {
      console.log("Promise fetch success:", data.length, "items");
      return data;
    })
    .catch(err => console.error("Promise fetch error:", err.message));
}

function handleFormSubmit(e) {
  e.preventDefault();

  const form     = e.target;
  const name     = form.elements["regName"].value.trim();
  const email    = form.elements["regEmail"].value.trim();
  const eventSel = form.elements["regEvent"].value;

  const nameErr  = document.getElementById("err-name");
  const emailErr = document.getElementById("err-email");
  const eventErr = document.getElementById("err-event");
  [nameErr, emailErr, eventErr].forEach(el => { if (el) el.textContent = ""; });

  let valid = true;
  if (!name)     { if (nameErr)  nameErr.textContent  = "Name is required.";  valid = false; }
  if (!email || !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
    if (emailErr) emailErr.textContent = "Valid email is required."; valid = false;
  }
  if (!eventSel) { if (eventErr) eventErr.textContent = "Please select an event."; valid = false; }
  if (!valid) return;

  submitRegistration({ name, email, event: eventSel });
}

function submitRegistration(payload) {
  const statusEl = document.getElementById("formStatus");
  if (statusEl) { statusEl.textContent = "⏳ Submitting..."; statusEl.style.color = "#888"; }

  setTimeout(() => {
    fetch("https://jsonplaceholder.typicode.com/posts", {
      method:  "POST",
      headers: { "Content-Type": "application/json" },
      body:    JSON.stringify(payload)
    })
      .then(res => {
        if (!res.ok) throw new Error(`Server error: ${res.status}`);
        return res.json();
      })
      .then(data => {
        console.log("✅ Registration submitted:", data);
        if (statusEl) { statusEl.textContent = "✅ Registration successful!"; statusEl.style.color = "green"; }
        console.log("📦 Submitted payload:", payload);
        document.getElementById("regForm")?.reset();
      })
      .catch(err => {
        console.error("Submit error:", err.message);
        if (statusEl) { statusEl.textContent = `❌ Submission failed: ${err.message}`; statusEl.style.color = "red"; }
      });
  }, 1000);
}

function initJQuery() {
  if (!window.$) return;

  $("#jqRegisterBtn").click(function () {
    const name = $("#jqName").val().trim();
    if (!name) { alert("Please enter your name."); return; }
    $("#jqResult").text(`✅ ${name}, you have been registered via jQuery!`).hide().fadeIn(600);
  });

  $(".eventCard").hide().fadeIn(800);

  $(document).on("click", ".dismiss-btn", function () {
    $(this).closest(".eventCard").fadeOut(400);
  });

  console.log("jQuery initialized. Version:", $.fn.jquery);
}

function debugLog(step, data) {
  console.group(`🔍 Debug: ${step}`);
  console.log(data);
  console.groupEnd();
}

function createQuickEvent(name, category = "general", seats = 50, fee = 0) {
  const id = eventsData.length + 1;
  return new CommunityEvent(id, name, category, "TBD", "2025-12-01", seats, fee);
}

window.addEventListener("load", function () {
  alert("Welcome to the Community Portal! 🎉");

  renderEvents(eventsData);

  const form = document.getElementById("regForm");
  if (form) form.addEventListener("submit", handleFormSubmit);

  const searchInput = document.getElementById("searchInput");
  if (searchInput) searchInput.addEventListener("keydown", handleSearchKeydown);

  const catFilter = document.getElementById("categoryFilter");
  if (catFilter) catFilter.addEventListener("change", e => handleCategoryFilter(e.target.value));

  const fetchBtn = document.getElementById("fetchEventsBtn");
  if (fetchBtn) fetchBtn.addEventListener("click", fetchEventsFromAPI);

  const addForm = document.getElementById("addEventForm");
  if (addForm) addForm.addEventListener("submit", function (e) {
    e.preventDefault();
    const name     = document.getElementById("newEventName").value.trim();
    const category = document.getElementById("newEventCategory").value;
    const seats    = parseInt(document.getElementById("newEventSeats").value) || 50;
    if (!name) return;
    const newEv = createQuickEvent(name, category, seats);
    addEvent(eventsData, newEv);
    debugLog("New event added", newEv);
    addForm.reset();
  });

  initJQuery();

  debugLog("Initial eventsData", eventsData.map(e => e.name));
  debugLog("Category counts", categoryCounter.getAll());

  const musicEvents = filterEventsByCategory(eventsData, e => e.category === "music");
  console.log("🎵 Music events:", musicEvents.map(e => e.name));

  console.log("📋 First event details:");
  logEventDetails(eventsData[0]);
});
