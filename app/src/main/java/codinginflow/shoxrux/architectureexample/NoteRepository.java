package codinginflow.shoxrux.architectureexample;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note){
        new InsertNotesAsyncTask(noteDao).execute(note);
    }

    public void update(Note note){
        new UpdateNotesAsyncTask(noteDao).execute(note);
    }

    public void delete(Note note){
        new DeleteNotesAsyncTask(noteDao).execute(note);
    }

    public void deleteAllNotes(){
        new DeleteAllNotesNotesAsyncTask(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    private class InsertNotesAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        public InsertNotesAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private class UpdateNotesAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        public UpdateNotesAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private class DeleteNotesAsyncTask extends AsyncTask<Note,Void,Void>{

        private NoteDao noteDao;

        public DeleteNotesAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private class DeleteAllNotesNotesAsyncTask extends AsyncTask<Void,Void,Void>{

        private NoteDao noteDao;

        public DeleteAllNotesNotesAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllnotes();
            return null;
        }
    }
}
